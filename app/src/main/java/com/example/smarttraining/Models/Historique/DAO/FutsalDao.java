package com.example.smarttraining.Models.Historique.DAO;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smarttraining.Models.Historique.Futsal;

import java.util.List;

@Dao
public interface FutsalDao {

    // Get all data query
    @Query("SELECT * FROM Futsal")
    List<Futsal> getItems();

    // Insert query
    @Insert(onConflict = REPLACE)
    void insertItem(Futsal item);

    // Delete query
    @Delete
    void deleteItem(Futsal item);

    // Delete all query
    @Delete
    void deleteAllItem(List<Futsal> items);
}
