package com.example.smarttraining.Models.Historique;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Handball.class}, version = 1, exportSchema = false)
public abstract class SaveMyMatchesDataBase extends RoomDatabase {

    // --- SINGLETON ---
    private static  volatile SaveMyMatchesDataBase INSTANCE;

    // --- DAO ---
    public abstract HandballDao handballDao();


    // --- INSTANCE ---
    public static SaveMyMatchesDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SaveMyMatchesDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaveMyMatchesDataBase.class, "MyDatabase.db")
                            //.addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
