package com.example.smarttraining.Models.Historique.DAO;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.smarttraining.Models.Historique.BasketBall;

import java.util.List;

@Dao
public interface BasketBallDao {

    // Get all data query
    @Query("SELECT * FROM BasketBall")
    List<BasketBall> getItems();

    // Insert query
    @Insert(onConflict = REPLACE)
    void insertItem(BasketBall item);

    // Delete query
    @Delete
    void deleteItem(BasketBall item);

    // Delete all query
    @Delete
    void deleteAllItem(List<BasketBall> items);
}
