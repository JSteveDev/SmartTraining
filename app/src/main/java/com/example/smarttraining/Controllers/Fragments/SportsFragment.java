package com.example.smarttraining.Controllers.Fragments;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.smarttraining.Adapters.HandballAdapter;
import com.example.smarttraining.Adapters.SportItemAdapter;
import com.example.smarttraining.Models.Historique.SaveMyMatchesDataBase;
import com.example.smarttraining.R;

import butterknife.BindView;

public class SportsFragment extends BaseFragment {

	@BindView(R.id.fragment_sport_recyclerView)
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
		return R.layout.fragment_sports;
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

		this.recyclerView.setAdapter(new SportItemAdapter(getResources().getStringArray(R.array.sportNameList), getResources().getStringArray(R.array.sportImageList)));
	}

}
