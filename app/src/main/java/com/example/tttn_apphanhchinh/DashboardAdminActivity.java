package com.example.tttn_apphanhchinh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tttn_apphanhchinh.admin.Fragment1;
import com.example.tttn_apphanhchinh.admin.Fragment2;
import com.example.tttn_apphanhchinh.admin.Fragment3;
import com.example.tttn_apphanhchinh.databinding.ActivityDashboardAdminBinding;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardAdminActivity extends AppCompatActivity {
    private ActivityDashboardAdminBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        replaceFragment(new Fragment1());


        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.ic_home) {
                replaceFragment(new Fragment1());
            } else if (item.getItemId() == R.id.ic_manga) {
                replaceFragment(new Fragment2());
            } else if (item.getItemId() == R.id.ic_menubook) {
                replaceFragment(new Fragment3());
            } else if (item.getItemId() == R.id.ic_user) {
//                replaceFragment(new PersonFragment());
            }
            return true;
        });

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}