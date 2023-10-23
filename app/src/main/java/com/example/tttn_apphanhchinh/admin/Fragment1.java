package com.example.tttn_apphanhchinh.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tttn_apphanhchinh.DashboardAdminActivity;
import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.UserAddActivity;
import com.example.tttn_apphanhchinh.databinding.Fragment1Binding;
import com.google.firebase.auth.FirebaseAuth;


public class Fragment1 extends Fragment {
    private FirebaseAuth firebaseAuth;
    private Fragment1Binding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(inflater, container, false); // Sửa tại đây
        // Init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        binding.addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UserAddActivity.class)); // Sửa tại đây
            }
        });
        return binding.getRoot();
    }
}



