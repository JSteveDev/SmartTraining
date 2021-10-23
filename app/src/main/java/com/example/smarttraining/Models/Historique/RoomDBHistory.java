package com.example.smarttraining.Models.Historique;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Handball.class}, version = 1, exportSchema = false)
public abstract class RoomDBHistory extends RoomDatabase {

    // --- SINGLETON ---
    private static RoomDBHistory INSTANCE;

    // --- DAO ---
    public abstract HandballDao handballDao();

    // --- DATABASE NAME ---
    private static String DATABASE_NAME = "database.db" ;


    // --- INSTANCE ---
    public synchronized static RoomDBHistory getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDBHistory.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
                }

        return INSTANCE;
    }
}
