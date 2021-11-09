package com.example.smarttraining.Models.Historique.DAO;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smarttraining.Models.Historique.Badminton;

import java.util.List;

@Dao
public interface BadmintonDao {

    // Get all data query
    @Query("SELECT * FROM Badminton")
    List<Badminton> getItems();

    // Insert query
    @Insert(onConflict = REPLACE)
    void insertItem(Badminton item);

    // Delete query
    @Delete
    void deleteItem(Badminton item);

    // Delete all query
    @Delete
    void deleteAllItem(List<Badminton> items);
}
