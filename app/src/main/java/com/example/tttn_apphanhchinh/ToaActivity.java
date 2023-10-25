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

    // Progress dialog
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
        initToaRecyclerView();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.backBtn.setOnClickListener(v -> onBackPressed());

        // Xử lý sự kiện khi nhấn nút đăng ký
        binding.submitToaBtn.setOnClickListener(v -> validateData());
    }

    private void validateData() {
        // Lấy dữ liệu từ trường nhập tòa
        String toa = binding.nameToaEt.getText().toString().trim();

        if (TextUtils.isEmpty(toa)) {
            // Hiển thị thông báo lỗi nếu trường tòa trống
            Toast.makeText(this, "Vui lòng nhập tòa", Toast.LENGTH_SHORT).show();
        } else {
            // Thêm tòa vào Firebase
            addToaToFirebase(toa);
        }
    }

    private void addToaToFirebase(String toa) {
        // Hiển thị progress dialog
        progressDialog.setMessage("Đang thêm tòa...");
        progressDialog.show();



        // Thiết lập thông tin để thêm vào Firebase DB
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("nameToa", toa);


        // Thêm vào Firebase DB: Database Root > Toa > toaId > toa info
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Toa");
        ref.child("" )
                .setValue(hashMap)
                .addOnSuccessListener(unused -> {
                    // Thêm thành công
                    progressDialog.dismiss();
                    Toast.makeText(ToaActivity.this, "Đã thêm tòa thành công.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ToaActivity.this, DashboardAdminActivity.class));
                })
                .addOnFailureListener(e -> {
                    // Thêm thất bại
                    progressDialog.dismiss();
                    Toast.makeText(ToaActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void initToaRecyclerView() {
        // Khởi tạo ArrayList để chứa danh sách tòa
        toaArrayList = new ArrayList<>();

        // Lấy danh sách tòa từ Firebase: Database Root > Toa
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Toa");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Xóa dữ liệu cũ trong ArrayList trước khi thêm dữ liệu mới
                toaArrayList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    // Lấy dữ liệu từ Firebase
                    ModelToa toa = ds.getValue(ModelToa.class);
                    // Thêm vào ArrayList
                    toaArrayList.add(toa);
                }

                // Khởi tạo adapter và thiết lập cho RecyclerView
                adapterToa = new AdapterToa(ToaActivity.this, toaArrayList);
                binding.categoriesRv.setLayoutManager(new LinearLayoutManager(ToaActivity.this));
                binding.categoriesRv.setAdapter(adapterToa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xảy ra lỗi
            }
        });
    }
}
