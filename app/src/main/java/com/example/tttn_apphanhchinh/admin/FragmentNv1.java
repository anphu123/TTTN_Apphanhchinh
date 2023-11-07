package com.example.tttn_apphanhchinh.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tttn_apphanhchinh.ActivitySuco;
import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.ToaActivity;
import com.example.tttn_apphanhchinh.UserAddActivity;
import com.example.tttn_apphanhchinh.databinding.Fragment1Binding;
import com.example.tttn_apphanhchinh.databinding.FragmentNv1Binding;
import com.google.firebase.auth.FirebaseAuth;


public class FragmentNv1 extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FragmentNv1Binding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNv1Binding.inflate(inflater, container, false); // Sửa tại đây
        // Init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ActivitySuco.class)); // Sửa tại đây
            }
        });

        return binding.getRoot();
    }
}

