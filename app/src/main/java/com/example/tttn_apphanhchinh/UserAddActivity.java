package com.example.tttn_apphanhchinh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.tttn_apphanhchinh.databinding.ActivityUserAddBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserAddActivity extends AppCompatActivity {
    private String name = "", email = "", password = "" ,userType = "",cPassword="";
    //view binding
    private ActivityUserAddBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Khởi tạo Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.backBtn.setOnClickListener(v -> onBackPressed());

        // Xử lý sự kiện khi nhấn nút đăng ký
        binding.submitBtn.setOnClickListener(v -> {
            boolean isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked) {
                createUserAccount();
            }
        });
    }

    private boolean CheckAllFields() {
        name = binding.nameTil.getEditText().getText().toString();
        email = binding.emailTil.getEditText().getText().toString().trim();
        password = binding.passwordTil.getEditText().getText().toString().trim();
        cPassword = binding.cPasswordTil.getEditText().getText().toString().trim();

        RadioGroup radioGroup = findViewById(R.id.radiogp);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select a user style", Toast.LENGTH_SHORT).show();
            return false;
        }

        RadioButton radioButton = findViewById(selectedId);
        userType = radioButton.getText().toString();

        return validateName() && validateEmail() && validatePassWord() && validateRepeatPassword() && password.equals(cPassword);
    }

    private boolean validateEmail() {
        String emailInput = binding.emailTil.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            binding.emailTil.setError("Email cannot be blank");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            binding.emailTil.setError("Please enter a valid email address");
            return false;
        } else {
            binding.emailTil.setError(null);
            return true;
        }
    }

    private boolean validateRepeatPassword() {
        String repeatPassword = binding.cPasswordTil.getEditText().getText().toString().trim();

        if (repeatPassword.isEmpty()) {
            binding.cPasswordTil.setError("Repeat password cannot be blank");
            return false;
        } else {
            binding.cPasswordTil.setError(null);
            return true;
        }
    }

    private boolean validatePassWord() {
        String passwordInput = binding.passwordTil.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            binding.passwordTil.setError("Password cannot be blank");
            return false;
        } else if (passwordInput.length() < 6) {
            binding.passwordTil.setError("Password must have at least 6 characters");
            return false;
        } else {
            binding.passwordTil.setError(null);
            return true;
        }
    }

    private boolean validateName() {
        String usernameInput = binding.nameTil.getEditText().getText().toString();

        if (usernameInput.isEmpty()) {
            binding.nameTil.setError("Name cannot be blank");
            return false;
        } else if (usernameInput.length() > 20) {
            binding.nameTil.setError("Username is too long");
            return false;
        } else {
            binding.nameTil.setError(null);
            return true;
        }
    }

    private void createUserAccount() {
        progressDialog.setMessage("Creating account...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    updateUserInfo();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(UserAddActivity.this, "Account creation failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void updateUserInfo() {
        progressDialog.setMessage("Saving user info...");

        long timestamp = System.currentTimeMillis();
        String uid = firebaseAuth.getUid();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("email", email);
        hashMap.put("name", name);
        hashMap.put("profileImage", ""); // Thêm hình ảnh sau
        hashMap.put("userType",userType);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(unused -> {
                    progressDialog.dismiss();
                    Toast.makeText(UserAddActivity.this, "Account created...", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(UserAddActivity.this, "Data failed adding to database: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
