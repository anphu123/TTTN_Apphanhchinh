package com.example.tttn_apphanhchinh.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tttn_apphanhchinh.databinding.RowCategoryBinding;
import com.example.tttn_apphanhchinh.model.ModelToa;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterToa extends RecyclerView.Adapter<AdapterToa.HolderToa> implements Filterable {

    private Context context;
    public ArrayList<ModelToa> toaArrayList, filterList;

    public AdapterToa(Context context, ArrayList<ModelToa> toaArrayList) {
        this.context = context;
        this.toaArrayList = toaArrayList;
        this.filterList = toaArrayList;
    }

    private RowCategoryBinding binding;

    // instance of our filter class
    private FilterToa filter;

    @NonNull
    @Override
    public HolderToa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // binding row_category.xml
        binding = RowCategoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderToa(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderToa holder, int position) {
        // get data
        ModelToa model = toaArrayList.get(position);
        String toa = model.getToa();

        // set data
        holder.categoryTV.setText(toa);

        // handle click, delete toa
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm delete dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setMessage("Bạn có muốn xóa tòa này?")
                        .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // begin delete
                                Toast.makeText(context, "Đang xóa...", Toast.LENGTH_SHORT).show();
                                deleteToa(model);
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User canceled the delete action
                            }
                        }).show();
            }
        });
    }

    private void deleteToa(ModelToa model) {
        // get id of toa to delete
        String id = model.getId();

        // Firebase DB > Toa > toaId
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Toa");
        ref.child(id)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // delete successfully
                        Toast.makeText(context, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // failed to delete
                        Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return toaArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new FilterToa(filterList, this);
        }
        return filter;
    }

    class HolderToa extends RecyclerView.ViewHolder {
        // UI views of row_category.xml
        TextView categoryTV;
        ImageButton deleteBtn;

        public HolderToa(@NonNull View itemView) {
            super(itemView);
            // initialize UI views
            categoryTV = binding.categoryTv;
            deleteBtn = binding.deleteBtn;
        }
    }
}
