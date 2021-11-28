package com.example.smarttraining.Controllers.Activities.Tennis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.smarttraining.Controllers.Activities.Badminton.BadmintonConfig;
import com.example.smarttraining.Controllers.Activities.Badminton.BadmintonMatch;
import com.example.smarttraining.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class TennisConfig extends AppCompatActivity {

    @BindView(R.id.tennis_config_mode_jeu)
    Button game_mode;
    @BindView(R.id.tennis_config_set)
    Button nb_set_gagnant;
    @BindView(R.id.tennis_config_play)
    Button play;

    private EditText[] joueurs_equipe1;
    private EditText[] joueurs_equipe2;

    @State int nb_set_gagnant_value = 2;
    @State int game_mode_value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tennis_config);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Icepick.restoreInstanceState(this, savedInstanceState);
        ButterKnife.bind(this);

        this.configureToolbar();
        this.initialisation_equipes();
        this.initialisation_buttons();
        this.initialisation_view_values();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
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

    private void initialisation_equipes() {
        joueurs_equipe1 = new EditText[2];
        joueurs_equipe1[0] = findViewById(R.id.tennis_config_eq1_j1);
        joueurs_equipe1[1] = findViewById(R.id.tennis_config_eq1_j2);

        joueurs_equipe2 = new EditText[2];
        joueurs_equipe2[0] = findViewById(R.id.tennis_config_eq2_j1);
        joueurs_equipe2[1] = findViewById(R.id.tennis_config_eq2_j2);

        switch (game_mode_value){
            case 0: //SIMPLE MODE
                joueurs_equipe1[1].setVisibility(View.GONE);
                joueurs_equipe2[1].setVisibility(View.GONE);
                break;
            case 1: //DOUBLE MODE
                joueurs_equipe1[1].setVisibility(View.VISIBLE);
                joueurs_equipe2[1].setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initialisation_view_values() {
        this.nb_set_gagnant.setText(String.valueOf(nb_set_gagnant_value));

        switch (game_mode_value){
            case 0:
                this.game_mode.setText(getResources().getString(R.string.simple_mode));
                break;
            case 1:
                this.game_mode.setText(getResources().getString(R.string.double_mode));
                break;
            default:
        }
    }

    private void initialisation_buttons() {
        game_mode.setOnClickListener(v -> SetModejeu());

        nb_set_gagnant.setOnClickListener(v -> SetNbSet());

        play.setOnClickListener(view -> {
            Intent otherActivity = new Intent(TennisConfig.this, BadmintonMatch.class);

            String[] team1Members = new String[]{joueurs_equipe1[0].getText().toString().trim(), joueurs_equipe1[1].getText().toString().trim()};
            String[] team2Members = new String[]{joueurs_equipe2[0].getText().toString().trim(), joueurs_equipe2[1].getText().toString().trim()};

            otherActivity.putExtra("team1_members", team1Members);
            otherActivity.putExtra("team2_members", team2Members);
            otherActivity.putExtra("nb_set_gagnant", nb_set_gagnant_value);
            otherActivity.putExtra("game_mode", game_mode_value);
            startActivity(otherActivity);
        });
    }

    private void SetModejeu() {

        ViewGroup.LayoutParams layout = joueurs_equipe1[1].getLayoutParams();

        switch (game_mode_value) {
            case 0: //SIMPLE MODE
                game_mode_value = 1;
                game_mode.setText(getResources().getString(R.string.double_mode));
                joueurs_equipe1[1].setVisibility(View.VISIBLE);
                joueurs_equipe2[1].setVisibility(View.VISIBLE);

                break;
            case 1: //DOUBLE MODE
                game_mode_value = 0;
                game_mode.setText(getResources().getString(R.string.simple_mode));
                joueurs_equipe1[1].setVisibility(View.GONE);
                joueurs_equipe2[1].setVisibility(View.GONE);
                break;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }

        joueurs_equipe1[1].setLayoutParams(layout);
        joueurs_equipe2[1].setLayoutParams(layout);
    }

    private void SetNbSet() {

        switch (nb_set_gagnant_value) {
            case 1:
                nb_set_gagnant_value = 2;
                break;
            case 2:
                nb_set_gagnant_value = 3;
                break;
            case 3:
                nb_set_gagnant_value = 1;
                break;
            default:
        }

        nb_set_gagnant.setText(String.valueOf(nb_set_gagnant_value));
    }
}