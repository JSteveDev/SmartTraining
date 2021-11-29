package com.example.smarttraining.Controllers.Activities.Badminton;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttraining.Models.Historique.Badminton;
import com.example.smarttraining.Models.Historique.RoomDBHistory;
import com.example.smarttraining.R;
import com.example.smarttraining.Views.Chronometer;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

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

    @State TextView[] set_score;

    private Chronometer chronometer;

    @State boolean timerRunning;
    @State boolean gameIsFinished = false;
    @State long time;
    @State int current_team_1_score;
    @State int current_team_2_score;
    @State int current_set;

    @State int set_team1;
    @State int set_team2;
    @State int[][] scores = new int[3][2];

    private int nb_set_gagnant;
    private int nb_point;
    private int nb_point_max;
    private int game_mode;
    private String[][] teamsMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton_match);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Icepick.restoreInstanceState(this, savedInstanceState);
        ButterKnife.bind(this);

        this.buttons_configurations();
        this.RetrieveParameters();
        this.PlayerDisplay();
        this.SetScoreDisplay();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        time = chronometer.getTime();
        Icepick.saveInstanceState(this, outState);
    }

    private void SetScoreDisplay() {
        this.set_score = new TextView[3];

        for (int i = 0; i < set_score.length; i++) {
            int resID = getResources().getIdentifier("badminton_match_score_set_" + (i + 1), "id", getPackageName());
            this.set_score[i] = findViewById(resID);
            if ((scores[i][0] == 0) && (scores[i][1] == 0)){
                this.set_score[i].setText(getResources().getString(R.string.undefined_score, "-", "-"));
            } else {
                this.set_score[i].setText(getResources().getString(R.string.undefined_score, String.valueOf(scores[i][0]), String.valueOf(scores[i][1])));
            }
            if (nb_set_gagnant == 1) {
                this.set_score[i].setLayoutParams(new LinearLayout.LayoutParams(0, 0, 0));
            }
        }

        this.team1Score.setText(String.valueOf(current_team_1_score));
        this.team2Score.setText(String.valueOf(current_team_2_score));

        this.team1Set.setText(String.valueOf(set_team1));
        this.team2Set.setText(String.valueOf(set_team2));

        chronometer = new Chronometer(this, timer, Chronometer.STATIC_DISPLAY, time);

        if (timerRunning){
            chronometer.start();
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
            SaveDialog();
        }
    }

    private void SaveDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = this.getLayoutInflater().inflate(R.layout.save_dialog_box, null);
        builder.setView(view);

        final Button Save_Button = view.findViewById(R.id.save_dialog_Save);
        final Button Exit_Button = view.findViewById(R.id.save_dialog_Exit);

        ViewStub layout = view.findViewById(R.id.sports_details);
        layout.setLayoutResource(R.layout.badminton_details);
        layout.inflate();

        TextView winner_declaration = view.findViewById(R.id.badminton_details_winner_declaration);
        TextView set_details = view.findViewById(R.id.badminton_details_set_value);

        switch (game_mode) {

            case 0: //SIMPLE MODE
                String player1 = getResources().getString(R.string.player_1);
                String player2 = getResources().getString(R.string.player_2);
                if (set_team1 >= nb_set_gagnant) {
                    if (teamsMembers[0][0].isEmpty()) {
                        winner_declaration.setText(getResources().getString(R.string.badminton_winner_declaration_simple, player1, set_team1, set_team2));
                    } else {
                        winner_declaration.setText(getResources().getString(R.string.badminton_winner_declaration_simple, teamsMembers[0][0], set_team1, set_team2));
                    }
                } else {
                    if (teamsMembers[1][0].isEmpty()) {
                        winner_declaration.setText(getResources().getString(R.string.badminton_winner_declaration_simple, player2, set_team2, set_team1));
                    }
                    winner_declaration.setText(getResources().getString(R.string.badminton_winner_declaration_simple, teamsMembers[1][0], set_team2, set_team1));
                }
                break;

            case 1: //DOUBLE MODE
                if (set_team1 >= nb_set_gagnant) {
                    if (team1Name.getText().toString().split("\\s+").length == 3) {
                        winner_declaration.setText(getResources().getString(R.string.badminton_winner_declaration_double, teamsMembers[0][0], teamsMembers[0][1], set_team1, set_team2));
                    } else {
                        String members;

                        if (teamsMembers[0][0].isEmpty()) {
                            if (teamsMembers[0][1].isEmpty()) {
                                members = getResources().getString(R.string.player_1);
                            } else {
                                members = teamsMembers[0][1];
                            }
                        } else {
                            members = teamsMembers[0][0];
                        }

                        winner_declaration.setText(getResources().getString(R.string.badminton_winner_declaration_simple, members, set_team1, set_team2));

                    }

                } else {
                    if (team2Name.getText().toString().split("\\s+").length == 3) {
                        winner_declaration.setText(getResources().getString(R.string.badminton_winner_declaration_double, teamsMembers[1][0], teamsMembers[1][1], set_team2, set_team1));
                    } else {
                        String members;

                        if (teamsMembers[1][0].isEmpty()) {
                            if (teamsMembers[1][1].isEmpty()) {
                                members = getResources().getString(R.string.player_2);
                            } else {
                                members = teamsMembers[1][1];
                            }
                        } else {
                            members = teamsMembers[1][0];
                        }

                        winner_declaration.setText(getResources().getString(R.string.badminton_winner_declaration_simple, members, set_team2, set_team1));
                    }
                }
                break;
            default:
        }

        switch (current_set) {
            case 1:
                set_details.setText(getResources().getString(R.string.badminton_details_score,
                        set_score[0].getText().toString(), "", ""));
                break;
            case 2:
                set_details.setText(getResources().getString(R.string.badminton_details_score,
                        set_score[0].getText().toString(), set_score[1].getText().toString(), ""));
                break;
            case 3:
                set_details.setText(getResources().getString(R.string.badminton_details_score,
                        set_score[0].getText().toString(), set_score[1].getText().toString(), set_score[2].getText().toString()));
                break;
            default:
                set_details.setText("");
        }

        final AlertDialog Picker_dialog = builder.create();
        Picker_dialog.show();

        Save_Button.setOnClickListener(v1 -> {
            save_match();
            Toast.makeText(this, "Saved !", Toast.LENGTH_SHORT).show();
            finish();
        });

        Exit_Button.setOnClickListener(v1 -> finish());
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
        scores[current_set][0] = current_team_1_score;
        scores[current_set][1] = current_team_2_score;
        this.set_score[current_set].setText(getResources().getString(R.string.undefined_score, String.valueOf(scores[current_set][0]), String.valueOf(scores[current_set][1])));
    }

    private void PlayerDisplay() {
        switch (game_mode) {
            case 0: //SIMPLE MODE

                if (teamsMembers[0][0].isEmpty()) {
                    team1Name.setText(getResources().getString(R.string.player_1));
                } else {
                    team1Name.setText(teamsMembers[0][0]);
                }

                if (teamsMembers[1][0].isEmpty()) {
                    team2Name.setText(getResources().getString(R.string.player_1));
                } else {
                    team2Name.setText(teamsMembers[1][0]);
                }
                break;

            case 1: //DOUBLE MODE

                if (teamsMembers[0][0].isEmpty()) {
                    if (teamsMembers[0][1].isEmpty()) {
                        team1Name.setText(getResources().getString(R.string.player_1));
                    } else {
                        team1Name.setText(teamsMembers[0][1]);
                    }
                } else {
                    if (teamsMembers[0][1].isEmpty()) {
                        team1Name.setText(teamsMembers[0][0]);
                    } else {
                        team1Name.setText(getResources().getString(R.string.player_display, teamsMembers[0][0], teamsMembers[0][1]));
                    }
                }

                if (teamsMembers[1][0].isEmpty()) {
                    if (teamsMembers[1][1].isEmpty()) {
                        team2Name.setText(getResources().getString(R.string.player_1));
                    } else {
                        team2Name.setText(teamsMembers[1][1]);
                    }
                } else {
                    if (teamsMembers[1][1].isEmpty()) {
                        team2Name.setText(teamsMembers[1][0]);
                    } else {
                        team2Name.setText(getResources().getString(R.string.player_display, teamsMembers[1][0], teamsMembers[1][1]));
                    }
                }
        }
    }

    private void RetrieveParameters() {
        Bundle bundle = getIntent().getExtras();
        this.nb_point = bundle.getInt("nb_point");
        this.nb_set_gagnant = bundle.getInt("nb_set_gagnant");
        this.nb_point_max = bundle.getInt("nb_point_max");
        this.game_mode = bundle.getInt("game_mode");
        this.teamsMembers = new String[2][2];
        this.teamsMembers[0] = bundle.getStringArray("team1_members");
        this.teamsMembers[1] = bundle.getStringArray("team2_members");
    }

}