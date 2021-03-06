package com.example.smarttraining.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smarttraining.Controllers.Fragments.SportsFragment;
import com.example.smarttraining.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


	@BindView(R.id.activity_main_bottom_navigation)
	BottomNavigationView mBottomNavigationView;
	private SportsFragment sportsFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		ButterKnife.bind(this);

		this.configureAndShowSportsFragment();
		this.configureBottomView();
		this.configureToolbar();
	}

	private void configureBottomView() {
		mBottomNavigationView.setSelectedItemId(R.id.action_arbitration);
		mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.action_arbitration:
						return true;
					case R.id.action_history:
						startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
						overridePendingTransition(0,0);
						return  true;
				}
				return false;
			}
		});
	}

	private void configureAndShowSportsFragment() {
		// A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
		sportsFragment = (SportsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);

		if (sportsFragment == null) {
			// B - Create new main fragment
			sportsFragment = new SportsFragment();
			// C - Add it to FrameLayout container
			getSupportFragmentManager().beginTransaction()
					.add(R.id.activity_main_frame_layout, sportsFragment)
					.commit();
		}
	}

	private void configureToolbar() {
		// Get the toolbar view inside the activity layout
		Toolbar toolbar = findViewById(R.id.toolbar);
		// Sets the toolbar
		setSupportActionBar(toolbar);
	}
}