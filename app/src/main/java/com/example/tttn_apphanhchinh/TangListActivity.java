package com.example.tttn_apphanhchinh;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tttn_apphanhchinh.adapter.TangAdapter;
import com.example.tttn_apphanhchinh.databinding.ActivityTangListBinding;
import com.example.tttn_apphanhchinh.model.ModelTang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TangListActivity extends AppCompatActivity {

    private ActivityTangListBinding binding;
    private TangAdapter tangAdapter;
    private List<String> tangList;
    private ArrayList<ModelTang> tangArrayList;
    private String toaTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTangListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Intent intent = getIntent();
//        toaTitle = intent.getStringExtra("id");

        // Load the list of floors
        loadTangList();

        // Initialize and set the adapter for the RecyclerView
        tangArrayList = new ArrayList<>();
        tangAdapter = new TangAdapter(this, tangArrayList);
        binding.recyclerView1.setAdapter(tangAdapter);
    }

    private void loadTangList() {
        tangArrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tang");
        ref.orderByChild("id").equalTo(toaTitle)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        tangArrayList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelTang model = ds.getValue(ModelTang.class);
                            tangArrayList.add(model);
                        }
                        tangAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
