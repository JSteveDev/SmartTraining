package com.example.smarttraining.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.example.smarttraining.Controllers.Fragments.SportsFragment;
import com.example.smarttraining.Models.Historique.Handball;
import com.example.smarttraining.Models.Historique.HandballDao;
import com.example.smarttraining.Models.Historique.SaveMyMatchesDataBase;
import com.example.smarttraining.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	private SaveMyMatchesDataBase dataBase;

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

		dataBase = Room.inMemoryDatabaseBuilder(getApplicationContext(),
				SaveMyMatchesDataBase.class)
				.allowMainThreadQueries()
				.build();

		/*dataBase.handballDao().insertItem(new Handball("Handball", "Cap1", "Cap2",
				"Nantes", "Poitiers",3,3,30));*/


		Toast.makeText(getApplicationContext(), String.valueOf(dataBase.handballDao().getItems().size()), Toast.LENGTH_SHORT).show();
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