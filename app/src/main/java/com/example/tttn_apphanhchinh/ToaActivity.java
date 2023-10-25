package com.example.tttn_apphanhchinh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tttn_apphanhchinh.databinding.ActivityToaBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ToaActivity extends AppCompatActivity {
    private String nameToa = "";
    private ActivityToaBinding binding;
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;
    private ArrayList<ModelToa> toaArrayList;
    private AdapterToa adapterToa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityToaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Khởi tạo Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.backBtn.setOnClickListener(v -> onBackPressed());

        // Xử lý sự kiện khi nhấn nút đăng ký
        binding.submitToaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

    }

    private String Toa = "";

    private void validateData() {

        /*Before adding validate data*/

        //get data
        Toa = binding.nameToaEt.getText().toString().trim();
        if (TextUtils.isEmpty(Toa)) {
            Toast.makeText(this, "Please enter category", Toast.LENGTH_SHORT).show();
        } else {
            addCategoryFirebase();
        }
    }

    private void addCategoryFirebase() {
        //show progress
        progressDialog.setMessage("Adding category ...");
        progressDialog.show();

        //get timestamp

        long timestamp = System.currentTimeMillis();

        //setup info to add firebase db

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", "" + timestamp);
        hashMap.put("Toa", "" + Toa);
        hashMap.put("timestamp", timestamp);


        //add to firebase db .... Database Root > Category > categoryId > category info

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tòa");
        ref.child("" + timestamp)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //category add success
                        progressDialog.dismiss();
                        Toast.makeText(ToaActivity.this, "Category added successfully...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ToaActivity.this, DashboardAdminActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //category add failed
                        progressDialog.dismiss();
                        Toast.makeText(ToaActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}