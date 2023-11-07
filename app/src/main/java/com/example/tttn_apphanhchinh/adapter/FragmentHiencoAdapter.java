package com.example.tttn_apphanhchinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.model.ModelSuco;

import java.util.ArrayList;

public class FragmentHiencoAdapter extends RecyclerView.Adapter<FragmentHiencoAdapter.ViewHolder>{

    private ArrayList<ModelSuco> list;

    public FragmentHiencoAdapter(ArrayList<ModelSuco> list, Context context){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FragmentHiencoAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hienco, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.toa.setText(list.get(position).getToa());
        holder.room.setText(list.get(position).getRoom());
        holder.time.setText(list.get(position).getTime());
        holder.data.setText(list.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, toa, room, time, data;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.idname);
            toa = itemView.findViewById(R.id.tvToa);
            room = itemView.findViewById(R.id.tvRoom);
            time = itemView.findViewById(R.id.tvTime);
            data = itemView.findViewById(R.id.tvDate);
        }
    }
}
