package com.example.tttn_apphanhchinh.adapter;

import android.widget.Filter;
import com.example.tttn_apphanhchinh.model.ModelToa;
import java.util.ArrayList;

public class FilterToa extends Filter {
    ArrayList<ModelToa> filterList;
    AdapterToa adapterToa;

    public FilterToa(ArrayList<ModelToa> filterList, AdapterToa adapterToa) {
        this.filterList = filterList;
        this.adapterToa = adapterToa;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelToa> filterModels = new ArrayList<>();

            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getToa().toUpperCase().contains(constraint)) {
                    filterModels.add(filterList.get(i));
                }
            }
            results.count = filterModels.size();
            results.values = filterModels;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterToa.toaArrayList = (ArrayList<ModelToa>) results.values;
        adapterToa.notifyDataSetChanged();
    }
}
