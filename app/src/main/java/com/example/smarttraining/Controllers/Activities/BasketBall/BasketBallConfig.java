package com.example.smarttraining.Controllers.Activities.BasketBall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smarttraining.Controllers.Activities.Badminton.BadmintonMatch;
import com.example.smarttraining.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class BasketBallConfig extends AppCompatActivity {

    @BindView(R.id.basket_config_period)
    Button period;
    @BindView(R.id.basket_config_display_players)
    Button display_player;
    @BindView(R.id.basket_config_nb_player)
    Button nb_player;
    @BindView(R.id.basket_config_play)
    Button play;

    @State
    int period_value = 10;
    @State
    int nb_player_value = 6;
    @State
    boolean display_player_value = false;

    private static final int NUMBER_PLAYER = 12;
    private EditText[] joueurs_equipe1;
    private EditText[] joueurs_equipe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_ball_config);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Icepick.restoreInstanceState(this, savedInstanceState);
        ButterKnife.bind(this);

        this.ConfigureToolbar();
        this.Initialisation_buttons();
        this.Initialisation_equipes();
        this.Initialisation_view_values();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private void ConfigureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        // Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void Initialisation_buttons() {
        period.setOnClickListener(v -> SetPeriodPicker());

        display_player.setOnClickListener(view -> SetDisplayPlayer());

        nb_player.setOnClickListener(view -> SetNbPlayer());

        play.setOnClickListener(view -> Launch_Match());
    }

    private void Launch_Match(){
        Intent otherActivity = new Intent(BasketBallConfig.this, BadmintonMatch.class);

        String[] team1Members = new String[nb_player_value];
        String[] team2Members = new String[nb_player_value];
        for (int i=0; i<nb_player_value;i++){
            team1Members[i] = joueurs_equipe1[i].getText().toString().trim();
            team2Members[i] = joueurs_equipe2[i].getText().toString().trim();
        }

        otherActivity.putExtra("team1Members", team1Members);
        otherActivity.putExtra("team2Members", team2Members);
        otherActivity.putExtra("period", period_value);
        otherActivity.putExtra("nb_player", nb_player_value);
        otherActivity.putExtra("display_player", display_player_value);
        startActivity(otherActivity);
    }

    private void Initialisation_view_values() {
        period.setText(getResources().getString(R.string.period_value,period_value));
        this.nb_player.setText(String.valueOf(nb_player_value));

        if (display_player_value) {
            display_player.setText(getResources().getString(R.string.oui));
        } else {
            display_player.setText(getResources().getString(R.string.non));
        }

        DisplayPlayer();
    }

    private void SetPeriodPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = this.getLayoutInflater().inflate(R.layout.scroll_picker_1, null);
        builder.setView(view);

        final NumberPicker picker = view.findViewById(R.id.scroll_picker);
        final Button Ok_button = view.findViewById(R.id.dialog_picker_OK);

        int min = 1;
        int max = 30;

        picker.setMaxValue(max);
        picker.setMinValue(min);
        picker.setValue(period_value);

        final AlertDialog Picker_dialog = builder.create();
        Picker_dialog.show();

        Ok_button.setOnClickListener(v1 -> {
            period_value = picker.getValue();
            period.setText(getResources().getString(R.string.period_value,period_value));
            Picker_dialog.dismiss();
        });
    }

    private void SetDisplayPlayer() {
        if (display_player_value) {
            display_player_value = false;
            display_player.setText(getResources().getString(R.string.non));
        } else {
            display_player_value = true;
            display_player.setText(getResources().getString(R.string.oui));
        }

        DisplayPlayer();
    }

    private void DisplayPlayer(){

        if (display_player_value){
            for (int i=0; i<NUMBER_PLAYER; i++) {
                if (i < nb_player_value) {
                    joueurs_equipe1[i].setVisibility(View.VISIBLE);
                    joueurs_equipe2[i].setVisibility(View.VISIBLE);
                } else {
                    joueurs_equipe1[i].setVisibility(View.GONE);
                    joueurs_equipe2[i].setVisibility(View.GONE);
                }
            }

        } else {

            for (int i=0; i<NUMBER_PLAYER; i++){
                joueurs_equipe1[i].setVisibility(View.GONE);
                joueurs_equipe2[i].setVisibility(View.GONE);
            }
        }
    }

    private void SetNbPlayer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = this.getLayoutInflater().inflate(R.layout.scroll_picker_1, null);
        builder.setView(view);

        final NumberPicker picker = view.findViewById(R.id.scroll_picker);
        final Button Ok_button = view.findViewById(R.id.dialog_picker_OK);

        picker.setMaxValue(NUMBER_PLAYER);
        picker.setMinValue(1);
        picker.setValue(nb_player_value);

        final AlertDialog Picker_dialog = builder.create();
        Picker_dialog.show();

        Ok_button.setOnClickListener(v1 -> {
            nb_player_value = picker.getValue();
            nb_player.setText(String.valueOf(nb_player_value));
            DisplayPlayer();
            Picker_dialog.dismiss();
        });
    }

    private void Initialisation_equipes() {
        joueurs_equipe1 = new EditText[NUMBER_PLAYER];
        joueurs_equipe2 = new EditText[NUMBER_PLAYER];

        for (int i=0; i < NUMBER_PLAYER; i++){
            String player_index = "Joueur " +(i+1);

            int resID_1 = getResources().getIdentifier("basket_config_eq1_j" + (i + 1), "id", getPackageName());
            joueurs_equipe1[i] = findViewById(resID_1);
            joueurs_equipe1[i].setHint(player_index);

            int resID_2 = getResources().getIdentifier("basket_config_eq2_j" + (i + 1), "id", getPackageName());
            joueurs_equipe2[i] = findViewById(resID_2);
            joueurs_equipe2[i].setHint(player_index);
        }
    }

}