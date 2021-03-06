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

public class HistoryActivity extends AppCompatActivity {


    @BindView(R.id.activity_history_bottom_navigation)
    BottomNavigationView mBottomNavigationView;
    private HistoryFragment historyActivityFragment;
    private HistoryDetailFragment historyDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        this.configureAndShowHistoryFragment();
        this.configureBottomView();
        this.configureToolbar();
    }

    private void configureBottomView() {
        mBottomNavigationView.setSelectedItemId(R.id.action_history);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_arbitration:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_history:
                        return true;
                }
                return false;
            }
        });
    }

    private void configureAndShowHistoryFragment() {
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        historyActivityFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(R.id.activity_history_frame_layout);

        if (historyActivityFragment == null) {
            // B - Create new main fragment
            historyActivityFragment = new HistoryFragment();
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_history_frame_layout, historyActivityFragment)
                    .commit();
        }

        //We only add HistoryDetailFragment in Tablet mode (If found activity_detail_history_frame_layout)
        historyDetailFragment = (HistoryDetailFragment) getSupportFragmentManager().findFragmentById(R.id.activity_detail_history_frame_layout);

        //A - We only add DetailFragment in Tablet mode (If found frame_layout_detail)
        if (historyDetailFragment == null && findViewById(R.id.activity_detail_history_frame_layout) != null) {
            historyDetailFragment = new HistoryDetailFragment();
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
    }
}