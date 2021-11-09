package com.example.smarttraining.Controllers.Fragments;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.smarttraining.Adapters.BadmintonAdapter;
import com.example.smarttraining.Adapters.BasketballAdapter;
import com.example.smarttraining.Adapters.FutsalAdapter;
import com.example.smarttraining.Adapters.HandballAdapter;
import com.example.smarttraining.Adapters.UltimateAdapter;
import com.example.smarttraining.Adapters.VolleyballAdapter;
import com.example.smarttraining.Models.Historique.Badminton;
import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.R;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.fragment_sport_history_recyclerView)
    RecyclerView recyclerView;

    private RoomDBHistory dataBase;
    private static final String KEY_POSITION="position";

    // --------------
    // BASE METHODS
    // --------------


    public static HistoryFragment newInstance(int position) {

        HistoryFragment frag = new HistoryFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return frag;
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
        int position = getArguments().getInt(KEY_POSITION, -1);

        switch (position){
            case 0: // Badminton
                this.recyclerView.setAdapter(new BadmintonAdapter(getActivity(),  dataBase.badmintonDao().getItems()));
                break;
            case 1: // Basket-ball
                this.recyclerView.setAdapter(new BasketballAdapter(getActivity(),  dataBase.basketBallDao().getItems()));
                break;
            case 2: // Crossfit
                break;
            case 3: // Escalade
                break;
            case 4: // Futsal
                this.recyclerView.setAdapter(new FutsalAdapter(getActivity(),  dataBase.futsalDao().getItems()));
                break;
            case 5: // Handball
                this.recyclerView.setAdapter(new HandballAdapter(getActivity(),  dataBase.handballDao().getItems()));
                break;
            case 6: // Tennis
                break;
            case 7: // Ultimate
                this.recyclerView.setAdapter(new UltimateAdapter(getActivity(),  dataBase.ultimateDao().getItems()));
                break;
            case 8: // Volleyball
                this.recyclerView.setAdapter(new VolleyballAdapter(getActivity(),  dataBase.volleyballDao().getItems()));
                break;
            default:
        }


    }

}
