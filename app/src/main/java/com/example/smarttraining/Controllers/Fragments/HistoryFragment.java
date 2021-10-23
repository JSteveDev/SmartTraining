package com.example.smarttraining.Controllers.Fragments;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.smarttraining.Adapters.HandballAdapter;
import com.example.smarttraining.Models.Historique.SaveMyMatchesDataBase;
import com.example.smarttraining.R;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.fragment_sport_history_recyclerView)
    RecyclerView recyclerView;


    private SaveMyMatchesDataBase dataBase;

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

        dataBase = Room.inMemoryDatabaseBuilder(getActivity().getApplicationContext(),
                SaveMyMatchesDataBase.class)
                .allowMainThreadQueries()
                .build();

        Toast.makeText(getContext(), String.valueOf(dataBase.handballDao().getItems().size()), Toast.LENGTH_SHORT).show();

        this.recyclerView.setAdapter(new HandballAdapter(dataBase.handballDao().getItems()));
    }

}
