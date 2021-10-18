package com.example.smarttraining.Controllers.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import icepick.Icepick;

public abstract class BaseFragment extends Fragment {

	// Force developer implement those methods
	protected abstract BaseFragment newInstance();
	protected abstract int getFragmentLayout();
	protected abstract void configureDesign();
	protected abstract void updateDesign();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Get layout identifier from abstract method
		View view = inflater.inflate(getFragmentLayout(), container, false);
		// Binding Views
		ButterKnife.bind(this, view);
		// Configure Design (Developer will call this method instead of override onCreateView())
		this.configureDesign();
		return(view);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// Handling Bundle Restoration
		Icepick.restoreInstanceState(this, savedInstanceState);
		// Update Design (Developer will call this method instead of override onActivityCreated())
		this.updateDesign();
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		// Handling Bundle Save
		Icepick.saveInstanceState(this, outState);
	}
}
