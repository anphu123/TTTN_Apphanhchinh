package com.example.tttn_apphanhchinh.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.adapter.FragmentNv2Adapter;
import com.example.tttn_apphanhchinh.databinding.FragmentNv2Binding;
import com.example.tttn_apphanhchinh.model.ModelNv2;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentNv2 extends Fragment {

    FragmentNv2Binding binding;
    FragmentNv2Adapter adapter;
    private ArrayList<ModelNv2> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNv2Binding.inflate(inflater, container, false);

        binding.rvlist.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new ModelNv2("Nguyễn Văn A", "20/10/2023", "14:00 AM", "R204"));
        list.add(new ModelNv2("Nguyễn Văn b", "21/10/2023", "15:00 AM", "R205"));
        list.add(new ModelNv2("Nguyễn Văn c", "22/10/2023", "16:00 AM", "R206"));

        adapter = new FragmentNv2Adapter(list, getContext());
        binding.rvlist.setAdapter(adapter);

        return binding.getRoot();
    }
}