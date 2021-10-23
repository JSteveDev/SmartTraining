package com.example.smarttraining.Controllers.Fragments;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.smarttraining.Adapters.HandballAdapter;
import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.R;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.fragment_sport_history_recyclerView)
    RecyclerView recyclerView;

    private RoomDBHistory dataBase;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected BaseFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_history;
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
        dataBase = RoomDBHistory.getInstance(getActivity());

        this.recyclerView.setAdapter(new HandballAdapter(getActivity(),  dataBase.handballDao().getItems()));
    }

}
