package com.example.smarttraining.Models.Historique.DAO;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smarttraining.Models.Historique.Ultimate;

import java.util.List;

@Dao
public interface UltimateDao {

    // Get all data query
    @Query("SELECT * FROM Ultimate")
    List<Ultimate> getItems();

    // Insert query
    @Insert(onConflict = REPLACE)
    void insertItem(Ultimate item);

    // Delete query
    @Delete
    void deleteItem(Ultimate item);

    // Delete all query
    @Delete
    void deleteAllItem(List<Ultimate> items);
}
