package com.example.smarttraining.Models.Historique;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HandballDao {

    @Query("SELECT * FROM Handball")
    List<Handball> getItems();

    @Insert
    long insertItem(Handball item);

    @Update
    int updateItem(Handball item);

    @Query("DELETE FROM Handball WHERE id = :itemId")
    int deleteItem(long itemId);
}
