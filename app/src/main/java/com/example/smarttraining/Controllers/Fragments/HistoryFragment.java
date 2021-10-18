package com.example.smarttraining.Controllers.Fragments;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smarttraining.Adapters.SportIconAdapter;
import com.example.smarttraining.Adapters.SportItemAdapter;
import com.example.smarttraining.R;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment {

	@BindView(R.id.fragment_sport_icon_recyclerView)
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
		this.recyclerView.setAdapter(new SportIconAdapter(getResources().getStringArray(R.array.sportNameList), getResources().getStringArray(R.array.sportIconList)));
	}

}
