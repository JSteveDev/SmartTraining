package com.example.smarttraining.Models.Historique;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.smarttraining.Models.Historique.DAO.BadmintonDao;
import com.example.smarttraining.Models.Historique.DAO.BasketBallDao;
import com.example.smarttraining.Models.Historique.DAO.FutsalDao;
import com.example.smarttraining.Models.Historique.DAO.HandballDao;
import com.example.smarttraining.Models.Historique.DAO.UltimateDao;
import com.example.smarttraining.Models.Historique.DAO.VolleyballDao;

@Database(entities = {Badminton.class, BasketBall.class,Futsal.class, Handball.class, Ultimate.class, Volleyball.class}, version = 1, exportSchema = false)
public abstract class RoomDBHistory extends RoomDatabase {

    // --- SINGLETON ---
    private static RoomDBHistory INSTANCE;

    // --- DAO ---
    public abstract HandballDao handballDao();
    public abstract BasketBallDao basketBallDao();
    public abstract FutsalDao futsalDao();
    public abstract UltimateDao ultimateDao();
    public abstract VolleyballDao volleyballDao();
    public abstract BadmintonDao badmintonDao();

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
