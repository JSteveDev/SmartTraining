package com.example.smarttraining.Models.Historique.DAO;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smarttraining.Models.Historique.Handball;

import java.util.List;

@Dao
public interface HandballDao {

    // Get all data query
    @Query("SELECT * FROM Handball")
    List<Handball> getItems();

    // Insert query
    @Insert(onConflict = REPLACE)
    void insertItem(Handball item);

    // Delete query
    @Delete
    void deleteItem(Handball item);

    // Delete all query
    @Delete
    void deleteAllItem(List<Handball> items);
}
