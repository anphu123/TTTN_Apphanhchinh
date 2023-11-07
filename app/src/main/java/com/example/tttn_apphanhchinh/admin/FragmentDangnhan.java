package com.example.tttn_apphanhchinh.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.adapter.FragmentDangnhanAdapter;
import com.example.tttn_apphanhchinh.databinding.FragmentDangnhanBinding;
import com.example.tttn_apphanhchinh.model.ModelSuco;

import java.util.ArrayList;


public class FragmentDangnhan extends Fragment {

    FragmentDangnhanBinding binding;
    FragmentDangnhanAdapter adapter;

    private ArrayList<ModelSuco> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDangnhanBinding.inflate(inflater, container, false);


        binding.rvDangNhan.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new ModelSuco("Nguyễn Văn A", "Tòa T", "T810", "11/7/2023","11:00"));
        list.add(new ModelSuco("Nguyễn Văn B", "Tòa T", "T811", "11/7/2023","12:00"));
        list.add(new ModelSuco("Nguyễn Văn C", "Tòa T", "T812", "11/7/2023","13:00"));
        list.add(new ModelSuco("Nguyễn Văn D", "Tòa T", "T813", "11/7/2023","14:00"));

        adapter = new FragmentDangnhanAdapter(list, getContext());
        binding.rvDangNhan.setAdapter(adapter);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}