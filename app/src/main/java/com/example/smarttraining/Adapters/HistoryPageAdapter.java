package com.example.smarttraining.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.smarttraining.Controllers.Fragments.HistoryFragment;

public class HistoryPageAdapter extends FragmentStateAdapter {

    private String[] sportsNames;

    public HistoryPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String[] sportsNames) {
        super(fragmentManager, lifecycle);
        this.sportsNames = sportsNames;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return HistoryFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return sportsNames.length;
    }
}
