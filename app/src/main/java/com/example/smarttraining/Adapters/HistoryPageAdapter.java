package com.example.smarttraining.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.smarttraining.Controllers.Fragments.HistoryFragment;
import com.example.smarttraining.Controllers.Fragments.SportsFragment;

public class HistoryPageAdapter extends FragmentStateAdapter {

    private String[] sportsNames;

    public HistoryPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String[] sportsNames) {
        super(fragmentManager, lifecycle);
        this.sportsNames = sportsNames;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new HistoryFragment();
    }

    @Override
    public int getItemCount() {
        return sportsNames.length;
    }
}
