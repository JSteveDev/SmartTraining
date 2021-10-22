package com.example.smarttraining.Models.Historique;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.util.ArrayList;

@Entity
public class Handball{

    @PrimaryKey (autoGenerate = true)
    private long id;
    private String category;
    private Date date;
    private ArrayList<String> teamPlayers1;
    private ArrayList<String> teamPlayers2;
    private ArrayList<String> teamNames;
    private int[] score;
    private int period;

    public Handball(long id, String category, Date date, ArrayList<String> teamPlayers1, ArrayList<String> teamPlayers2, ArrayList<String> teamNames, int[] score, int period) {
        this.id = id;
        this.category = category;
        this.date = date;
        this.teamPlayers1 = teamPlayers1;
        this.teamPlayers2 = teamPlayers2;
        this.teamNames = teamNames;
        this.score = score;
        this.period = period;
    }

    //GETTERS
    public long getId() { return id; }
    public String getCategory() { return category; }
    public Date getDate() { return date; }
    public ArrayList<String> getTeamPlayers1() { return teamPlayers1; }
    public ArrayList<String> getTeamPlayers2() { return teamPlayers2; }
    public int[] getScore() { return score; }
    public ArrayList<String> getTeamNames() { return teamNames; }
    public int getPeriod() { return period; }

    // SETTERS
    public void setId(long id) { this.id = id; }
    public void setCategory(String category) { this.category = category; }
    public void setDate(Date date) { this.date = date; }
    public void setTeamPlayers1(ArrayList<String> teamPlayers) { this.teamPlayers1 = teamPlayers; }
    public void setTeamPlayers2(ArrayList<String> teamPlayers) { this.teamPlayers2 = teamPlayers; }
    public void setTeamNames(ArrayList<String> teamNames) { this.teamNames = teamNames; }
    public void setScore(int[] score) { this.score = score; }
    public void setPeriod(int period) { this.period = period; }
}