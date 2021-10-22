package com.example.smarttraining.Models.Historique;

import java.sql.Date;

public class SportHistory {

    private long id;
    private String category;
    private Date date;

    public SportHistory(long id, String category, Date date) {
        this.id = id;
        this.category = category;
        this.date = date;
    }

    //GETTERS
    public long getId() { return id; }
    public String getCategory() { return category; }
    public Date getDate() { return date; }

    // SETTERS
    public void setId(long id) { this.id = id; }
    public void setCategory(String category) { this.category = category; }
    public void setDate(Date date) { this.date = date; }
}
