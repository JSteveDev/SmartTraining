package com.example.smarttraining.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smarttraining.Controllers.Fragments.HistoryDetailFragment;
import com.example.smarttraining.Controllers.Fragments.HistoryFragment;
import com.example.smarttraining.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailHistoryActivity extends AppCompatActivity {

    private HistoryDetailFragment historyDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        this.configureAndShowHistoryFragment();
        this.configureToolbar();
    }


    private void configureAndShowHistoryFragment() {
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        historyDetailFragment = (HistoryDetailFragment) getSupportFragmentManager().findFragmentById(R.id.activity_detail_history_frame_layout);

        if (historyDetailFragment == null) {
            // B - Create new main fragment
            historyDetailFragment = new HistoryDetailFragment();
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_detail_history_frame_layout, historyDetailFragment)
                    .commit();
        }
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the toolbar
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        // Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}