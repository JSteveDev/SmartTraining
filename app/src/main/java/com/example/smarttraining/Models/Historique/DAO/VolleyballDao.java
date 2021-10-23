package com.example.smarttraining.Models.Historique.DAO;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smarttraining.Models.Historique.Volleyball;

import java.util.List;

@Dao
public interface VolleyballDao {

    // Get all data query
    @Query("SELECT * FROM Volleyball")
    List<Volleyball> getItems();

    // Insert query
    @Insert(onConflict = REPLACE)
    void insertItem(Volleyball item);

    // Delete query
    @Delete
    void deleteItem(Volleyball item);

    // Delete all query
    @Delete
    void deleteAllItem(List<Volleyball> items);
}
