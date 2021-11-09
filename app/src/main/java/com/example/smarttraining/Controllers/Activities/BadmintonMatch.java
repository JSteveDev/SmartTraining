package com.example.smarttraining.Controllers.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttraining.Models.Historique.Badminton;
import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.R;
import com.example.smarttraining.Views.Chronometer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BadmintonMatch extends AppCompatActivity {

    @BindView(R.id.badminton_match_score_team_1)
    TextView team1Score;
    @BindView(R.id.badminton_score_team_1_set)
    TextView team1Set;
    @BindView(R.id.badminton_match_score_team_2)
    TextView team2Score;
    @BindView(R.id.badminton_score_team_2_set)
    TextView team2Set;
    @BindView(R.id.badminton_timer)
    TextView timer;

    @BindView(R.id.badminton_match_name_team_1)
    TextView team1Name;
    @BindView(R.id.badminton_match_name_team_2)
    TextView team2Name;

    private TextView[] set_score;

    private Chronometer chronometer;
    private boolean timerRunning;
    private boolean gameIsFinished = false;

    private int current_team_1_score;
    private int current_team_2_score;
    private int current_set;

    private int set_team1;
    private int set_team2;

    private int nb_set_gagnant;
    private int nb_point;
    private int nb_point_max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton_match);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        chronometer = new Chronometer(this, timer, Chronometer.STATIC_DISPLAY);

        this.buttons_configurations();
        this.PlayerDisplay();
        this.RetrieveParameters();
        this.SetScoreDisplay();
    }

    private void SetScoreDisplay() {
        this.set_score = new TextView[3];

        for (int i=0; i<set_score.length;i++){
            int resID = getResources().getIdentifier("badminton_match_score_set_" + (i + 1), "id", getPackageName());
            this.set_score[i] = findViewById(resID);
            this.set_score[i].setText(getResources().getString(R.string.undefined_score, "-", "-"));

            if (nb_set_gagnant == 1){
                this.set_score[i].setLayoutParams(new LinearLayout.LayoutParams(0, 0, 0));
            }
        }
    }


    private void buttons_configurations() {
        this.timer.setOnClickListener(view -> {
            if (!gameIsFinished) chronometerPauseStart();
        });

        this.timer.setOnLongClickListener(view -> {
            if (!gameIsFinished) chronometerReset();
            return true;
        });

        this.team1Score.setOnClickListener(view -> {
            if (!gameIsFinished) IncreaseScore(1);
        });

        this.team1Score.setOnLongClickListener(view -> {
            if (!gameIsFinished) DecreaseScore(1);
            return true;
        });

        this.team2Score.setOnClickListener(view -> {
            if (!gameIsFinished) IncreaseScore(2);
        });

        this.team2Score.setOnLongClickListener(view -> {
            if (!gameIsFinished) DecreaseScore(2);
            return true;
        });

    }

    private void chronometerPauseStart() {
        if (!timerRunning) {
            chronometer.start();
            timerRunning = true;
        } else {
            chronometer.pause();
            timerRunning = false;
        }
    }

    private void chronometerReset() {
        chronometer.reset();
        timerRunning = false;
    }

    private void DecreaseScore(int team) {
        switch (team) {
            case 1:
                if (current_team_1_score > 0) {
                    current_team_1_score -= 1;
                }
                break;

            case 2:
                if (current_team_2_score > 0) {
                    current_team_2_score -= 1;
                }
                break;
            default:
        }
        ScoreRefresh();
    }

    private void IncreaseScore(int team) {
        switch (team) {
            case 1:
                current_team_1_score += 1;
                if (current_team_1_score >= nb_point) {
                    if (((current_team_1_score - current_team_2_score) >= 2) || (current_team_1_score >= nb_point_max)) {
                        SetDisplayRefresh();
                        current_set += 1;
                        current_team_1_score = 0;
                        current_team_2_score = 0;
                        set_team1 += 1;
                        team1Set.setText(String.valueOf(set_team1));
                        CheckWinConditions();
                    }
                }
                break;

            case 2:
                current_team_2_score += 1;
                if (current_team_2_score >= nb_point) {
                    if (((current_team_2_score - current_team_1_score) >= 2) || (current_team_2_score >= nb_point_max)) {
                        SetDisplayRefresh();
                        current_set += 1;
                        current_team_1_score = 0;
                        current_team_2_score = 0;
                        set_team2 += 1;
                        team2Set.setText(String.valueOf(set_team2));
                        CheckWinConditions();
                    }
                }
                break;
            default:
        }
        ScoreRefresh();
    }

    private void CheckWinConditions() {
        if ((set_team1 >= nb_set_gagnant) || (set_team2 >= nb_set_gagnant)) {
            if (timerRunning) {
                chronometer.pause();
            }
            gameIsFinished = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View view = this.getLayoutInflater().inflate(R.layout.save_dialog_box, null);
            builder.setView(view);

            final Button Save_Button = view.findViewById(R.id.save_dialog_Save);
            final Button Exit_Button = view.findViewById(R.id.save_dialog_Exit);

            final AlertDialog Picker_dialog = builder.create();
            Picker_dialog.show();

            Save_Button.setOnClickListener(v1 -> {
                save_match();
                Toast.makeText(this, "Saved !", Toast.LENGTH_SHORT).show();
                finish();
            });

            Exit_Button.setOnClickListener(v1 -> finish());
        }
    }

    private void save_match() {
        RoomDBHistory dataBase = RoomDBHistory.getInstance(this);

        String score = "";
        for (int i = 0; i < current_set - 1; i++) {
            score += set_score[i].getText().toString().trim() + "\n";
        }
        score += set_score[current_set - 1].getText().toString().trim();

        dataBase.badmintonDao().insertItem(new Badminton(team1Name.getText().toString().trim(), team2Name.getText().toString().trim(), score
                , nb_set_gagnant, nb_point, nb_point_max));
    }

    private void ScoreRefresh() {
        this.team1Score.setText(String.valueOf(current_team_1_score));
        this.team2Score.setText(String.valueOf(current_team_2_score));
    }

    private void SetDisplayRefresh() {
        this.set_score[current_set].setText(getResources().getString(R.string.undefined_score, String.valueOf(current_team_1_score), String.valueOf(current_team_2_score)));
    }

    private void PlayerDisplay() {
        Bundle bundle = getIntent().getExtras();
        String[] teamMembers = bundle.getStringArray("teams_members");
        team1Name.setText(teamMembers[0]);
        team2Name.setText(teamMembers[1]);
    }

    private void RetrieveParameters() {
        Bundle bundle = getIntent().getExtras();
        nb_point = bundle.getInt("nb_point");
        nb_set_gagnant = bundle.getInt("nb_set_gagnant");
        nb_point_max = bundle.getInt("nb_point_max");
    }


}