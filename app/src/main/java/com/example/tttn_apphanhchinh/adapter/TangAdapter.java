package com.example.tttn_apphanhchinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tttn_apphanhchinh.databinding.RowTangBinding;
import com.example.tttn_apphanhchinh.model.ModelTang;
import java.util.ArrayList;

public class TangAdapter extends RecyclerView.Adapter<TangAdapter.TangViewHolder> {

    private final Context context;
    private final ArrayList<ModelTang> tangArrayList;
    private final LayoutInflater inflater;

    public TangAdapter(Context context, ArrayList<ModelTang> tangArrayList) {
        this.context = context;
        this.tangArrayList = tangArrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowTangBinding binding = RowTangBinding.inflate(inflater, parent, false);
        return new TangViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TangViewHolder holder, int position) {
        ModelTang modelTang = tangArrayList.get(position);

        String toa = modelTang.getToa();
        String tang = modelTang.getTang();

        holder.binding.toatv.setText(toa);
        holder.binding.tangtv.setText(tang);
    }

    @Override
    public int getItemCount() {
        return tangArrayList.size();
    }

    static class TangViewHolder extends RecyclerView.ViewHolder {
        RowTangBinding binding;

        TangViewHolder(RowTangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
