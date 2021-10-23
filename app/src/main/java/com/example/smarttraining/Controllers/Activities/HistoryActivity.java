package com.example.smarttraining.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import androidx.viewpager2.widget.ViewPager2;

import com.example.smarttraining.Adapters.HistoryPageAdapter;
import com.example.smarttraining.Controllers.Fragments.HistoryFragment;
import com.example.smarttraining.Models.Historique.SaveMyMatchesDataBase;
import com.example.smarttraining.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class HistoryActivity extends AppCompatActivity {


    @BindView(R.id.activity_history_bottom_navigation)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.activity_history_viewpager)
    ViewPager2 pager;
    @BindView(R.id.activity_history_tablayout)
    TabLayout tabLayout;

    private SaveMyMatchesDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
        this.configureViewPager();
        this.configureBottomView();
        this.configureToolbar();

        dataBase = Room.inMemoryDatabaseBuilder(getApplicationContext(),
                SaveMyMatchesDataBase.class)
                .allowMainThreadQueries()
                .build();

        Toast.makeText(getApplicationContext(), String.valueOf(dataBase.handballDao().getItems().size()), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private void configureViewPager() {
        String[] sportNameList = getResources().getStringArray(R.array.sportNameList);

        pager.setAdapter(new HistoryPageAdapter(getSupportFragmentManager(), getLifecycle(), sportNameList));

        new TabLayoutMediator(tabLayout, pager,
                (tab, position) -> tab.setText(sportNameList[position])
        ).attach();

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the toolbar
        setSupportActionBar(toolbar);
    }
}