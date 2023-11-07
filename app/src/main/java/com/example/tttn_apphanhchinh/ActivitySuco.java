package com.example.tttn_apphanhchinh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.tttn_apphanhchinh.admin.FragmentDangnhan;
import com.example.tttn_apphanhchinh.admin.FragmentHienco;
import com.example.tttn_apphanhchinh.databinding.ActivitySucoBinding;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivitySuco extends AppCompatActivity {
    private ImageButton mImageButton, btnNotifi;
    

    ActivitySucoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySucoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        mImageButton = findViewById(R.id.imgback);
        btnNotifi = findViewById(R.id.imgThongbao);

        // Tạo Adapter cho ViewPager2
        FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return 2; // Số lượng tab
            }

            @Override
            public Fragment createFragment(int position) {
                if (position == 0) {
                    return new FragmentHienco();
                } else {
                    return new FragmentDangnhan();
                }
            }
        };

        // Thiết lập Adapter cho ViewPager2
        binding.vpLich.setAdapter(adapter);

        // Thiết lập TabLayout với ViewPager2
        binding.tabSuco.addTab(binding.tabSuco.newTab().setText("Sự cố hiện có"));
        binding.tabSuco.addTab(binding.tabSuco.newTab().setText("Đang tiếp nhận"));
        binding.tabSuco.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpLich.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        binding.vpLich.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabSuco.selectTab(binding.tabSuco.getTabAt(position));
            }
        });

        binding.imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.imgThongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySuco.this, NotifiActivity.class);
                startActivity(intent);
            }
        });

    }

}