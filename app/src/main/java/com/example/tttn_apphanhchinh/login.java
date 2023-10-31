package com.example.tttn_apphanhchinh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tttn_apphanhchinh.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //innit firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        //setup progressDialog

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        // handle click, go to register screen


        // handle click, begin login
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private String email = "", password = "";

    private boolean validateData() {
        // Before login, lets do some data validation
        //get data
        email = binding.emailEt.getText().toString().trim();
        password = binding.passwordEt.getText().toString().trim();

        //validate data
        if (!validateEmail() | !validatePassword()) {
            return false;
            //Toast.makeText(this, "Invalid email pattern...!", Toast.LENGTH_SHORT).show();
        } else {
            loginUser();
            return true;
            // data is validate, begin login
        }
    }

    private boolean validateEmail() {
        String inputE = binding.emailEt.getText().toString().trim();
        if (inputE.isEmpty()) {
            binding.emailTil.setError("Email cannot be blank");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailTil.setError("Invalid email pattern...!");
            return false;
        } else {
            binding.emailTil.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String inputPass = binding.passwordTil.getEditText().getText().toString().trim();
        if (inputPass.isEmpty()) {
            binding.passwordTil.setError("Password cannot be blank");
            return false;
        } else {
            binding.passwordTil.setError(null);
            return true;
        }
    }

    private void loginUser() {
        //show progress
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        //login user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //login success, check if user is user or admin
                        checkUser();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // login failed
                        progressDialog.dismiss();
                        Toast.makeText(login.this, "đăng nhập không thành công , vui lòng kiểm tra lại email hoặc mật khẩu" , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUser() {
        progressDialog.setMessage("Checking User...");

        // check if user is user or admin form realtime database
        // get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        //check in db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        progressDialog.dismiss();
                        // get user type
                        String userType = "" + snapshot.child("userType").getValue();
                        // check user type
                        if (userType.equals("Giáo viên")) {
                            // giáo viên đăng nhập , chuyển sang màn hình của gv
//                            startActivity(new Intent(login.this, DashboardGvActivity.class));
                            finish();
                        } else if (userType.equals("Quản trị viên")) {
                            // quản trị viên đăng nhập, chuyển sang màn hình của quản trị viên
                            startActivity((new Intent(login.this, DashboardAdminActivity.class)));
                            finish();
                        } else if (userType.equals("NVPB")) {
                            //nhân viên đăng nhập chuyển sang màn hình nhân viên
                            startActivity(new Intent(login.this, DashboardNvActivity.class));
                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
    }
}
