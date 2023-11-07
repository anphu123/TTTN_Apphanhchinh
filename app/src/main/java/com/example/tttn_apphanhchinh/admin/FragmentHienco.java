package com.example.tttn_apphanhchinh.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.adapter.FragmentDangnhanAdapter;
import com.example.tttn_apphanhchinh.adapter.FragmentHiencoAdapter;
import com.example.tttn_apphanhchinh.databinding.FragmentDangnhanBinding;
import com.example.tttn_apphanhchinh.databinding.FragmentHiencoBinding;
import com.example.tttn_apphanhchinh.model.ModelSuco;

import java.util.ArrayList;


public class FragmentHienco extends Fragment {

    FragmentHiencoBinding binding;
    FragmentHiencoAdapter adapter;

    private ArrayList<ModelSuco> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHiencoBinding.inflate(inflater, container, false);


        binding.rvHienCo.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new ModelSuco("Nguyễn Văn A", "Tòa T", "T810", "11/7/2023","11:00"));
        list.add(new ModelSuco("Nguyễn Văn B", "Tòa T", "T811", "11/7/2023","12:00"));
        list.add(new ModelSuco("Nguyễn Văn C", "Tòa T", "T812", "11/7/2023","13:00"));
        list.add(new ModelSuco("Nguyễn Văn D", "Tòa T", "T813", "11/7/2023","14:00"));

        adapter = new FragmentHiencoAdapter(list, getContext());
        binding.rvHienCo.setAdapter(adapter);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}