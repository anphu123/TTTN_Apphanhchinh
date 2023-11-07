package com.example.tttn_apphanhchinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tttn_apphanhchinh.adapter.AdapterNotifi;
import com.example.tttn_apphanhchinh.databinding.ActivityNotifiBinding;
import com.example.tttn_apphanhchinh.model.ModelNotifi;

import java.util.ArrayList;
import java.util.List;

public class NotifiActivity extends AppCompatActivity {

    ActivityNotifiBinding binding;
    private ArrayList<ModelNotifi> list;
    private RecyclerView recyclerView;
    private AdapterNotifi adapterNotifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi);

//        list.add(new ModelNotifi("Thong bao nop bang tot nghiep THPT hoc ki String nam 2023", "Nguoi dang: Nguyen A", "Thoi gian: 11/7/2023", "12:00"));
//        list.add(new ModelNotifi("Thong bao xet tot nghiep som", "Nguoi dang: Nguyen A", "Thoi gian: 11/7/2023", "12:00"));
//        list.add(new ModelNotifi("Thong bao nop hoc phi hoc ki Summer", "Nguoi dang: Nguyen A", "Thoi gian: 11/7/2023", "12:00"));
//
//        recyclerView = findViewById(R.id.vpNotifi);
//        adapterNotifi = new AdapterNotifi(list);
//        recyclerView.setAdapter(adapterNotifi);
    }
}