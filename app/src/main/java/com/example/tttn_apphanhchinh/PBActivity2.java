package com.example.tttn_apphanhchinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tttn_apphanhchinh.adapter.AdapterPb;
import com.example.tttn_apphanhchinh.model.ModelPb;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PBActivity2 extends AppCompatActivity {

    private EditText editTextDepartmentName, editTextDepartmentId, editTextHeadName, editTextPhoneNumber;
    private Button btnAddDepartment;
    private RecyclerView recyclerViewDepartments;

    private DatabaseReference databaseReference;
    private List<ModelPb> departmentList;
    private AdapterPb departmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbactivity2); // Change to the correct layout file

        editTextDepartmentName = findViewById(R.id.editTextDepartmentName);
        editTextDepartmentId = findViewById(R.id.editTextDepartmentId);
        editTextHeadName = findViewById(R.id.editTextHeadName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        btnAddDepartment = findViewById(R.id.btnAddDepartment);
        recyclerViewDepartments = findViewById(R.id.recyclerViewDepartments); // Make sure this ID matches your layout

        departmentList = new ArrayList<>();
        departmentAdapter = new AdapterPb(this, departmentList);

        recyclerViewDepartments.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDepartments.setAdapter(departmentAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Departments");

        btnAddDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDepartment();
            }
        });

        // Add a listener to fetch data from Firebase Realtime Database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                departmentList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelPb department = dataSnapshot.getValue(ModelPb.class);
                    departmentList.add(department);
                }
                departmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PBActivity2.this, "Failed to load departments", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDepartment() {
        String name = editTextDepartmentName.getText().toString().trim();
        String id = editTextDepartmentId.getText().toString().trim();
        String headName = editTextHeadName.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(id) && !TextUtils.isEmpty(headName) && !TextUtils.isEmpty(phoneNumber)) {
            // Create a new department object
            ModelPb department = new ModelPb(name, id, headName, phoneNumber);

            // Push the department object to the database
            String departmentId = databaseReference.push().getKey();
            databaseReference.child(departmentId).setValue(department);

            // Clear the input fields
            editTextDepartmentName.setText("");
            editTextDepartmentId.setText("");
            editTextHeadName.setText("");
            editTextPhoneNumber.setText("");

            Toast.makeText(this, "Department added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }
}
