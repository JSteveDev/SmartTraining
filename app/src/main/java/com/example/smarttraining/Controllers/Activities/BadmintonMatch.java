package com.example.smarttraining.Controllers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.smarttraining.R;
import com.example.smarttraining.Views.Chronometer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BadmintonMatch extends AppCompatActivity {

    @BindView (R.id.badminton_match_score_team_1) TextView team1Score;
    @BindView (R.id.badminton_match_score_team_2) TextView team2Score;
    @BindView (R.id.badminton_timer) TextView timer;

    private Chronometer chronometer;
    private boolean timerRunning;

    private int current_team_1_score = 5;
    private int current_team_2_score = 5;

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
    }


    private void buttons_configurations(){
        this.timer.setOnClickListener(view -> chronometerPauseStart());

        this.timer.setOnLongClickListener(view -> {
            chronometerReset();
            return true;
        });

        this.team1Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.team1Score.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DecreaseScore(1);
                return true;
            }
        });

        this.team2Score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.team2Score.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DecreaseScore(2);
                return true;
            }
        });

    }

    private void chronometerPauseStart(){
        if (!timerRunning){
            chronometer.start();
            timerRunning = true;
        }
        else {
            chronometer.pause();
            timerRunning = false;
        }
    }

    private void chronometerReset(){
        chronometer.reset();
        timerRunning = false;
    }

    private void DecreaseScore(int team){
        switch (team){
            case 1:
                if (current_team_1_score > 0){
                    current_team_1_score -= 1;
                }
                break;

            case 2:
                if (current_team_2_score > 0){
                    current_team_2_score -= 1;
                }
                break;

            default:
        }

        ScoreRefresh();
    }

    private void ScoreRefresh() {
        this.team1Score.setText(String.valueOf(current_team_1_score));
        this.team2Score.setText(String.valueOf(current_team_2_score));
    }

    private void PlayerDisplay(){

    }

    private void RetrieveParameters(){

    }

}