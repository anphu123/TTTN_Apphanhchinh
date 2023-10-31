//package com.example.tttn_apphanhchinh;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.example.tttn_apphanhchinh.databinding.ActivityDashboardGvBinding;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class DashboardGvActivity extends AppCompatActivity {
//
//    private ActivityDashboardGvBinding binding;
//    private FirebaseAuth firebaseAuth;
//    private ProgressDialog progressDialog;
//    private ArrayList<String>   toaArrayList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityDashboardGvBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        loadToa();
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Please wait");
//        progressDialog.setCanceledOnTouchOutside(false);
//        binding.backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        binding.categoryTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                categoryPickDialog();
//            }
//        });
//
//        //handle click, upload pdf
//        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //validate data
//                validateData();
//            }
//        });
//    }
//    private String title = "", description = "";
//    private void validateData() {
//
//        //get data
//        title = binding.titleEt.getText().toString().trim();
//        description = binding.descriptionEt.getText().toString().trim();
//
//        //validate data
//        if (TextUtils.isEmpty(title)) {
//            Toast.makeText(this, "Enter Title...", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(description)) {
//            Toast.makeText(this, "Enter Description...", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(selectedCategoryTitle)) {
//            Toast.makeText(this, "Pick Category...", Toast.LENGTH_SHORT).show();
//        } else if (pdfUri == null) {
//            Toast.makeText(this, "Pick Pdf...", Toast.LENGTH_SHORT).show();
//        } else {
//            //all data is valid, can upload now
//            uploadPdfToStorage();
//        }
//    }
//    private void loadPdfCategories() {
//
//        toaArrayList = new ArrayList<>();
//
//
//        //db reference to load categories... db > Categories
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Toa");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                categoryTitleArrayList.clear(); // clear before adding data
//                categoryIdArrayList.clear();
//                for (DataSnapshot ds : snapshot.getChildren()) {
//
//                    //get id and title of category
//                    String categoryId = "" + ds.child("id").getValue();
//                    String categoryTitle = "" + ds.child("category").getValue();
//
//                    //add to respective arraylists
//                    categoryTitleArrayList.add(categoryTitle);
//                    categoryIdArrayList.add(categoryId);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//
//
//}