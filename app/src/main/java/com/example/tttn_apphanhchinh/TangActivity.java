package com.example.tttn_apphanhchinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.tttn_apphanhchinh.databinding.ActivityTangBinding;
import com.example.tttn_apphanhchinh.databinding.ActivityToaBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TangActivity extends AppCompatActivity {
    private ActivityTangBinding binding;
    private FirebaseAuth firebaseAuth;
    private ArrayList<String> toaTitleArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        loadToa();


        //handle click, go to previous activity
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.toaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toaPickDialog();
            }
        });
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate data
                validateData();
            }
        });
    }

    private String tang = "";
    private void validateData() {
        tang=binding.tangEt.getText().toString().trim();

        if (TextUtils.isEmpty(tang)) {
            Toast.makeText(this, "Enter Title...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(selectedToaTitle)) {
            Toast.makeText(this, "chọn tòa",Toast.LENGTH_SHORT).show();

        }else {
            uploadTang(tang);
        }
    }
    private void uploadTang(String tang) {


        long timestamp = System.currentTimeMillis();
        DatabaseReference toaRef = FirebaseDatabase.getInstance().getReference("Tang").child(String.valueOf(timestamp));

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Tầng", tang);
        hashMap.put("Tòa ",selectedToaTitle);
        hashMap.put("timestamp", timestamp);

        toaRef.setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(TangActivity.this, "Tòa đã được thêm thành công...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(TangActivity.this, DashboardAdminActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(TangActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private  void loadToa(){
        toaTitleArrayList=new ArrayList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("toa");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                toaTitleArrayList.clear();

                for (DataSnapshot ds : snapshot.getChildren()){

                    String toaTitle= "" +ds.child("toa").getValue();

                    toaTitleArrayList.add(toaTitle);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private  String selectedToaTitle;

    private void toaPickDialog(){
        String[] toaArray=new String[toaTitleArrayList.size()];
        for (int i=0;i<toaTitleArrayList.size();i++) {
            toaArray[i]=toaTitleArrayList.get(i);
        }

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(("Toa"))
                .setItems(toaArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedToaTitle=toaTitleArrayList.get(which);


                        binding.toaTv.setText(selectedToaTitle);
                    }
                })
                .show();
    }
}