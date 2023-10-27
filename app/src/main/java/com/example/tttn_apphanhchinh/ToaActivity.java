package com.example.tttn_apphanhchinh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tttn_apphanhchinh.adapter.AdapterToa;
import com.example.tttn_apphanhchinh.databinding.ActivityToaBinding;
import com.example.tttn_apphanhchinh.model.ModelToa;
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

public class ToaActivity extends AppCompatActivity {
    private ActivityToaBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private ArrayList<ModelToa> toaArrayList;
    private AdapterToa adapterToa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityToaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        initToaRecyclerView();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Vui lòng chờ");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.backBtn.setOnClickListener(v -> onBackPressed());
        binding.submitToaBtn.setOnClickListener(v -> validateData());
    }

    private String toa = "";

    private void validateData() {
        toa = binding.nameToaEt.getText().toString().trim();
        if (TextUtils.isEmpty(toa)) {
            Toast.makeText(this, "Vui lòng nhập tòa", Toast.LENGTH_SHORT).show();
        } else {
            addToaToFirebase();
        }
    }

    private void addToaToFirebase() {
        progressDialog.setMessage("Đang thêm tòa...");
        progressDialog.show();
        long timestamp = System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", "" + timestamp);
        hashMap.put("toa", "" + toa);
        hashMap.put("timestamp", timestamp);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Toa");
        ref.child("" + timestamp)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(ToaActivity.this, "Tòa đã được thêm thành công...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ToaActivity.this, DashboardAdminActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(ToaActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initToaRecyclerView() {
        toaArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Toa");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                toaArrayList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    ModelToa toa = ds.getValue(ModelToa.class);
                    toaArrayList.add(toa);
                }

                adapterToa = new AdapterToa(ToaActivity.this, toaArrayList);
                binding.categoriesRv.setLayoutManager(new LinearLayoutManager(ToaActivity.this));
                binding.categoriesRv.setAdapter(adapterToa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ToaActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
