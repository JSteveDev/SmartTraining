package com.example.smarttraining.Controllers.Activities.Badminton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smarttraining.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

public class BadmintonConfig extends AppCompatActivity {

    @BindView(R.id.badminton_config_mode_jeu)
    Button game_mode;
    @BindView(R.id.badminton_config_set)
    Button nb_set_gagnant;
    @BindView(R.id.badminton_config_point)
    Button nb_point;
    @BindView(R.id.badminton_config_point_max)
    Button nb_point_max;
    @BindView(R.id.badminton_config_play)
    Button play;

    private EditText[] joueurs_equipe1;
    private EditText[] joueurs_equipe2;

    @State int nb_set_gagnant_value = 2;
    @State int nb_point_value = 21;
    @State int nb_point_max_value = 25;
    @State int game_mode_value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton_config);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Icepick.restoreInstanceState(this, savedInstanceState);
        ButterKnife.bind(this);

        this.configureToolbar();
        this.initialisation_equipes();
        this.initialisation_buttons();
        this.initialisation_view_values();
    }

    private void initialisation_view_values() {
        this.nb_point.setText(String.valueOf(nb_point_value));
        this.nb_point_max.setText(String.valueOf(nb_point_max_value));
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
        joueurs_equipe1[0] = findViewById(R.id.badminton_config_eq1_j1);
        joueurs_equipe1[1] = findViewById(R.id.badminton_config_eq1_j2);

        joueurs_equipe2 = new EditText[2];
        joueurs_equipe2[0] = findViewById(R.id.badminton_config_eq2_j1);
        joueurs_equipe2[1] = findViewById(R.id.badminton_config_eq2_j2);

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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    private void initialisation_buttons() {
        game_mode.setOnClickListener(v -> SetModejeu());

        nb_set_gagnant.setOnClickListener(v -> SetNbSet());

        nb_point.setOnClickListener(v -> SetNbPicker(0));

        nb_point_max.setOnClickListener(v -> SetNbPicker(1));

        play.setOnClickListener(view -> {
            Intent otherActivity = new Intent(BadmintonConfig.this, BadmintonMatch.class);

            String[] team1Members = new String[]{joueurs_equipe1[0].getText().toString().trim(), joueurs_equipe1[1].getText().toString().trim()};
            String[] team2Members = new String[]{joueurs_equipe2[0].getText().toString().trim(), joueurs_equipe2[1].getText().toString().trim()};

            otherActivity.putExtra("team1_members", team1Members);
            otherActivity.putExtra("team2_members", team2Members);
            otherActivity.putExtra("nb_set_gagnant", nb_set_gagnant_value);
            otherActivity.putExtra("nb_point", nb_point_value);
            otherActivity.putExtra("nb_point_max", nb_point_max_value);
            otherActivity.putExtra("game_mode", game_mode_value);
            startActivity(otherActivity);
        });
    }

    private void SetNbPicker(int button) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View view = this.getLayoutInflater().inflate(R.layout.scroll_picker_1, null);
            builder.setView(view);

            final NumberPicker picker = view.findViewById(R.id.scroll_picker);
            final Button Ok_button = view.findViewById(R.id.dialog_picker_OK);

            int min, max, nb;

            switch (button){
                case 0: //Point
                    min = 1;
                    max = nb_point_max_value;
                    nb = nb_point_value;
                    break;
                case 1: //Point max
                    min = nb_point_value;
                    max = 30;
                    nb = nb_point_max_value;
                    break;
                default:
                    min = 1;
                    max = 1;
                    nb = 1;
            }

            picker.setMaxValue(max);
            picker.setMinValue(min);
            picker.setValue(nb);

            final AlertDialog Picker_dialog = builder.create();
            Picker_dialog.show();

            Ok_button.setOnClickListener(v1 -> {
                int val = picker.getValue();
                switch (button){
                    case 0: //Point
                        nb_point_value = val;
                        nb_point.setText(String.valueOf(nb_point_value));
                        break;
                    case 1: //Point max
                        nb_point_max_value = val;
                        nb_point_max.setText(String.valueOf(nb_point_max_value));
                        break;
                    default:
                }
                Picker_dialog.dismiss();
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
    }

    private void SetNbSet() {

        switch (nb_set_gagnant_value) {
            case 1:
                nb_set_gagnant_value = 2;
                break;
            case 2:
                nb_set_gagnant_value = 1;
                break;
            default:
        }

        nb_set_gagnant.setText(String.valueOf(nb_set_gagnant_value));
    }
}