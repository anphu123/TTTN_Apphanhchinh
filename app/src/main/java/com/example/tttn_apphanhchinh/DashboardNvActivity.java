package com.example.tttn_apphanhchinh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tttn_apphanhchinh.admin.Fragment1;
import com.example.tttn_apphanhchinh.admin.Fragment2;
import com.example.tttn_apphanhchinh.admin.Fragment3;
import com.example.tttn_apphanhchinh.admin.FragmentNv1;
import com.example.tttn_apphanhchinh.admin.FragmentNv2;
import com.example.tttn_apphanhchinh.admin.FragmentNv3;
import com.example.tttn_apphanhchinh.admin.SettingFragment;
import com.example.tttn_apphanhchinh.databinding.ActivityDashboardAdminBinding;
import com.example.tttn_apphanhchinh.databinding.ActivityDashboardNvBinding;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardNvActivity extends AppCompatActivity {

    private ActivityDashboardNvBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardNvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        replaceFragment(new FragmentNv1());


        binding.bottomNavigationNv.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.ic_home2) {
                replaceFragment(new FragmentNv1());
            } else if (item.getItemId() == R.id.ic_manga2) {
                replaceFragment(new FragmentNv2());
            } else if (item.getItemId() == R.id.ic_setting2) {
                replaceFragment(new FragmentNv3());
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