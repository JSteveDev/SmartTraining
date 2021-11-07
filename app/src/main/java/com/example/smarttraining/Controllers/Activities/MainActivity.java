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
import com.example.smarttraining.Models.Historique.BasketBall;
import com.example.smarttraining.Models.Historique.Futsal;
import com.example.smarttraining.Models.Historique.Handball;
import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.Models.Historique.Ultimate;
import com.example.smarttraining.Models.Historique.Volleyball;
import com.example.smarttraining.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	private RoomDBHistory dataBase;

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



		dataBase = RoomDBHistory.getInstance(this);

		dataBase.handballDao().insertItem(new Handball("Maximilien", "Augustin",
				"Nantes", "Paris Saint Germain",3,3,30));

		dataBase.basketBallDao().insertItem(new BasketBall("Henry", "Pierre",
				"Nantes", "Paris Saint Germain",80,104,30));

		dataBase.futsalDao().insertItem(new Futsal("Henry", "Pierre",
				"Nantes", "Paris Saint Germain",0,2,20));

		dataBase.ultimateDao().insertItem(new Ultimate("Henry", "Pierre",
				"Nantes", "Paris Saint Germain",0,2));

		dataBase.volleyballDao().insertItem(new Volleyball("Henry", "Pierre",
				"Nantes", "Paris Saint Germain",0,2, 5, 25, 30));


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