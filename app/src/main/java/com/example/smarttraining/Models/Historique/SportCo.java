package com.example.smarttraining.Models.Historique;

import java.sql.Date;
import java.util.ArrayList;

public class SportCo extends SportHistory{

    private ArrayList<String[]> teamPlayers;
    private ArrayList<String> teamNames;
    private int[] score;

    public SportCo(long id, String category, Date date, ArrayList<String[]> teamPlayers, ArrayList<String> teamNames, int[] score) {
        super(id, category, date);
        this.teamPlayers = teamPlayers;
        this.teamNames = teamNames;
        this.score = score;
    }

    // GETTERS
    public ArrayList<String[]> getTeamPlayers() { return teamPlayers; }
    public int[] getScore() { return score; }
    public ArrayList<String> getTeamNames() { return teamNames; }

    // SETTERS
    public void setTeamPlayers(ArrayList<String[]> teamPlayers) { this.teamPlayers = teamPlayers; }
    public void setTeamNames(ArrayList<String> teamNames) { this.teamNames = teamNames; }
    public void setScore(int[] score) { this.score = score; }
}
