package com.example.smarttraining.Controllers.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smarttraining.Adapters.SportIconAdapter;
import com.example.smarttraining.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryDetailFragment extends BaseFragment {

    @BindView(R.id.fragment_sport_detail_recyclerView)
    RecyclerView recyclerView;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected BaseFragment newInstance() {
        return new SportsFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_history_detail;
    }

    @Override
    protected void configureDesign() {
        this.configureRecyclerView();
    }

    @Override
    protected void updateDesign() {
    }

    // --------------
    // UPDATE UI
    // --------------

    private void configureRecyclerView() {
        this.recyclerView.setAdapter(new SportIconAdapter(getResources().getStringArray(R.array.sportNameList), getResources().getStringArray(R.array.sportIconList)));
    }

}