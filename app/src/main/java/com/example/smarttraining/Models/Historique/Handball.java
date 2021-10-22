package com.example.smarttraining.Models.Historique;

import androidx.room.PrimaryKey;

import java.sql.Date;
import java.util.ArrayList;

public class Handball extends SportCo{

    private int periode;

    public Handball(long id, String category, Date date, ArrayList<String[]> teamPlayers, ArrayList<String> teamNames, int[] score, int periode) {
        super(id, category, date, teamPlayers, teamNames, score);
        this.periode = periode;
    }

    public int getPeriode() { return periode; }
    public void setPeriode(int periode) { this.periode = periode; }
}
