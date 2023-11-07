package com.example.tttn_apphanhchinh.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tttn_apphanhchinh.admin.FragmentDangnhan;
import com.example.tttn_apphanhchinh.admin.FragmentHienco;

public class FragmentSucoAdapter extends FragmentStateAdapter {
    public FragmentSucoAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentHienco();
            case 1:
                return new FragmentDangnhan();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
