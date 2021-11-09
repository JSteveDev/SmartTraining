package com.example.smarttraining.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smarttraining.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BadmintonConfig extends AppCompatActivity {

    @BindView(R.id.badminton_config_mode_jeu)
    Button mode_jeu;
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

    private String[] team1Members;
    private String[] team2Members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton_config);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        this.configureToolbar();
        this.initialisation_equipes();
        this.initialisation_buttons();
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

        joueurs_equipe1[1].getLayoutParams().height = 0;
        joueurs_equipe2[1].getLayoutParams().height = 0;
    }

    private void initialisation_buttons() {
        mode_jeu.setOnClickListener(v -> SetModejeu(mode_jeu));

        nb_set_gagnant.setOnClickListener(v -> SetNbSet(nb_set_gagnant));

        nb_point.setOnClickListener(v -> SetNbPicker(nb_point, 30));

        nb_point_max.setOnClickListener(v -> SetNbPicker(nb_point_max, 30));

        play.setOnClickListener(view -> {
            Intent otherActivity = new Intent(BadmintonConfig.this, BadmintonMatch.class);

            team1Members = new String[]{joueurs_equipe1[0].getText().toString().trim(), joueurs_equipe1[1].getText().toString().trim()};
            team2Members = new String[]{joueurs_equipe2[0].getText().toString().trim(), joueurs_equipe2[1].getText().toString().trim()};

            otherActivity.putExtra("team1_members", team1Members);
            otherActivity.putExtra("team2_members", team2Members);
            otherActivity.putExtra("nb_set_gagnant", Integer.parseInt(nb_set_gagnant.getText().toString()));
            otherActivity.putExtra("nb_point", Integer.parseInt(nb_point.getText().toString()));
            otherActivity.putExtra("nb_point_max", Integer.parseInt(nb_point_max.getText().toString()));
            otherActivity.putExtra("game_mode", mode_jeu.getText().toString());
            startActivity(otherActivity);
        });
    }

    private void SetNbPicker(Button button, int max) {
        button.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View view = this.getLayoutInflater().inflate(R.layout.scroll_picker_1, null);
            builder.setView(view);

            final NumberPicker picker = view.findViewById(R.id.scroll_picker);
            final Button Ok_button = view.findViewById(R.id.dialog_picker_OK);

            int nb = Integer.parseInt(button.getText().toString());
            int min = 1;
            if (button == nb_point_max)
                min = Integer.parseInt(nb_point.getText().toString().trim());

            picker.setMaxValue(max);
            picker.setMinValue(min);
            picker.setValue(nb);

            final AlertDialog Picker_dialog = builder.create();
            Picker_dialog.show();

            Ok_button.setOnClickListener(v1 -> {
                int val = picker.getValue();
                button.setText(String.valueOf(val));

                if (button == nb_point) {
                    int point_max = Integer.parseInt(nb_point_max.getText().toString().trim());
                    if (point_max < val) {
                        nb_point_max.setText(String.valueOf(val));
                    }
                }
                Picker_dialog.dismiss();
            });
        });
    }

    private void SetModejeu(Button button) {

        switch (button.getText().toString()) {
            case "SIMPLE":
                button.setText(R.string.double_mode);
                joueurs_equipe1[1].getLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
                joueurs_equipe2[1].getLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
                break;
            case "DOUBLE":
                button.setText(R.string.simple_mode);
                joueurs_equipe1[1].getLayoutParams().height = 0;
                joueurs_equipe2[1].getLayoutParams().height = 0;
                break;
            default:
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
    }

    private void SetNbSet(Button button) {

        switch (button.getText().toString()) {
            case "1":
                button.setText("2");
                break;
            case "2":
                button.setText("1");
                break;
            default:
        }
    }
}