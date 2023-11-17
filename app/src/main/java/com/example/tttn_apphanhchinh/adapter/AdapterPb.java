package com.example.tttn_apphanhchinh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tttn_apphanhchinh.R;
import com.example.tttn_apphanhchinh.model.ModelPb;

import java.util.List;

public class AdapterPb extends RecyclerView.Adapter<AdapterPb.ViewHolder> {

    private  Context context;
    private List<ModelPb> departmentList;

    public AdapterPb(Context context, List<ModelPb> departmentList) {
        this.context = context;
        this.departmentList = departmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_department, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelPb department = departmentList.get(position);
        holder.tvName.setText("Tên phòng ban: " + department.getName());
        holder.tvId.setText("ID: " + department.getId());
        holder.tvHeadName.setText("Trưởng phòng: " + department.getHeadName());
        holder.tvPhoneNumber.setText("Số điện thoại: " + department.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return departmentList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvId, tvHeadName, tvPhoneNumber;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvId = itemView.findViewById(R.id.tvId);
            tvHeadName = itemView.findViewById(R.id.tvHeadName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
        }
    }
}
