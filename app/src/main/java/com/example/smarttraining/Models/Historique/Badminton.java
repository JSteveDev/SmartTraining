package com.example.smarttraining.Models.Historique;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Badminton implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String team1Names;
    private String team2Names;
    private String score;
    private int nb_set_gagnant;
    private int nb_point;
    private int nb_point_max;


    public Badminton(String team1Names, String team2Names, String score, int nb_set_gagnant, int nb_point, int nb_point_max) {
        this.team1Names = team1Names;
        this.team2Names = team2Names;
        this.score = score;
        this.nb_set_gagnant = nb_set_gagnant;
        this.nb_point = nb_point;
        this.nb_point_max = nb_point_max;

        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        this.date = dateFormat.format(Calendar.getInstance().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeam1Names() {
        return team1Names;
    }

    public void setTeam1Names(String team1Names) {
        this.team1Names = team1Names;
    }

    public String getTeam2Names() {
        return team2Names;
    }

    public void setTeam2Names(String team2Names) {
        this.team2Names = team2Names;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getNb_set_gagnant() {
        return nb_set_gagnant;
    }

    public void setNb_set_gagnant(int nb_set_gagnant) {
        this.nb_set_gagnant = nb_set_gagnant;
    }

    public int getNb_point() {
        return nb_point;
    }

    public void setNb_point(int nb_point) {
        this.nb_point = nb_point;
    }

    public int getNb_point_max() {
        return nb_point_max;
    }

    public void setNb_point_max(int nb_point_max) {
        this.nb_point_max = nb_point_max;
    }
}