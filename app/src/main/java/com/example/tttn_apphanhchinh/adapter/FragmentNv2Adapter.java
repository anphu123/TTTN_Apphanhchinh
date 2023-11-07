package com.example.tttn_apphanhchinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.model.ModelNv2;

import java.util.ArrayList;

public class FragmentNv2Adapter extends RecyclerView.Adapter<FragmentNv2Adapter.ViewHolder>{

    private ArrayList<ModelNv2> list;

    public FragmentNv2Adapter(ArrayList<ModelNv2> list, Context context){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FragmentNv2Adapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nv2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.data.setText(list.get(position).getData());
        holder.time.setText(list.get(position).getTime());
        holder.room.setText(list.get(position).getData());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

    TextView name, data, time, room;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.namenv2);
        data = itemView.findViewById(R.id.Datanv2);
        time = itemView.findViewById(R.id.Timenv2);
        room = itemView.findViewById(R.id.Roomnv2);
    }
}
}
