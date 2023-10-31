package com.example.tttn_apphanhchinh.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.databinding.FragmentSettingBinding;
import com.example.tttn_apphanhchinh.login;
import com.google.firebase.auth.FirebaseAuth;


public class SettingFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    private FragmentSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        binding.linearAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.linearNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), login.class));
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}