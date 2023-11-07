package com.example.tttn_apphanhchinh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.model.ModelNotifi;

import java.util.ArrayList;

public class AdapterNotifi extends RecyclerView.Adapter<AdapterNotifi.ViewHolder> {

    private ArrayList<ModelNotifi> list;

    public AdapterNotifi(ArrayList<ModelNotifi> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterNotifi.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notifi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.idNews.setText(list.get(position).getIdNews());
        holder.idName.setText(list.get(position).getIdName());
        holder.idDate.setText(list.get(position).getIdDate());
        holder.idTime.setText(list.get(position).getIdTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView idNews, idName, idDate, idTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idNews = itemView.findViewById(R.id.idNews);
            idName = itemView.findViewById(R.id.tvNameNo);
            idDate = itemView.findViewById(R.id.tvTimedate);
            idTime = itemView.findViewById(R.id.tvTimeNo);
        }
    }
}
