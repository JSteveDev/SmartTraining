package com.example.smarttraining.Models.Historique;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Ultimate implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String date;
    private String captain1;
    private String captain2;
    private String teamName1;
    private String teamName2;
    private int score1;
    private int score2;

    public Ultimate(String captain1, String captain2, String teamName1, String teamName2, int score1, int score2) {
        this.captain1 = captain1;
        this.captain2 = captain2;
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.score1 = score1;
        this.score2 = score2;

        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        this.date = dateFormat.format(Calendar.getInstance().getTime());
    }

    // GETTERS
    public Integer getId() { return id; }
    public String getDate() { return date; }
    public String getCaptain1() { return captain1; }
    public String getCaptain2() { return captain2; }
    public String getTeamName1() { return teamName1; }
    public String getTeamName2() { return teamName2; }
    public int getScore1() { return score1; }
    public int getScore2() { return score2; }

    // SETTERS
    public void setDate(String date) { this.date = date; }
    public void setCaptain1(String captain1) { this.captain1 = captain1; }
    public void setCaptain2(String captain2) { this.captain2 = captain2; }
    public void setTeamName1(String teamName1) { this.teamName1 = teamName1; }
    public void setTeamName2(String teamName2) { this.teamName2 = teamName2; }
    public void setScore1(int score1) { this.score1 = score1; }
    public void setScore2(int score2) { this.score2 = score2; }
    public void setId(Integer id) { this.id = id; }
}