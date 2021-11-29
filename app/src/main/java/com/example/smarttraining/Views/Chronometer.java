package com.example.smarttraining.Views;

import android.app.Activity;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;



public class Chronometer {

    public static final int DYNAMIC_DISPLAY = 0;
    public static final int STATIC_DISPLAY = 1;

    private final Timer chronometre;
    private int heures, minutes, secondes, cent_secondes;
    private final TextView timeview;
    private TimerTask timerTask;
    private long time;
    private int displayFonction;
    private final Activity activity;

    public Chronometer(Activity activity, TextView timeview, int displayFonction, long time) {
        chronometre = new Timer();
        this.timeview = timeview;
        this.activity = activity;
        this.time = time;
        this.displayFonction = displayFonction;
    }

    public long getTime() { return time; }

    public int getHeures() { return heures; }

    public int getMinutes() { return minutes; }

    public int getSecondes() { return secondes; }

    public int getCent_secondes() { return cent_secondes; }

    public void reset() {
        if(timerTask != null) {
            timerTask.cancel();
            timeview.setText("00:00");
            time = 0;
        }
    }

    public void pause() {
        timerTask.cancel();
    }

    public void start() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time += 1;
                        timeview.setText(getTimerText());
                    }
                });
            }
        };
        chronometre.scheduleAtFixedRate(timerTask,0,10);
    }

    private String getTimerText() {
        int time_cent = (int) time;
        int time_sec = time_cent / 100;

        cent_secondes = time_cent % 100;
        secondes = ((time_sec % 86400) % 3600) % 60;
        minutes = ((time_sec % 86400) % 3600) / 60;
        heures = ((time_sec % 86400) / 3600);

        return  formatTime();
    }

    private String formatTime() {

        switch (displayFonction) {
            case DYNAMIC_DISPLAY:
                if (heures != 0) {
                    return String.format(Locale.FRANCE, "%02d:%02d", heures, minutes);
                } else {
                    if (minutes != 0) {
                        return String.format(Locale.FRANCE, "%02d:%02d", minutes, secondes);
                    } else {
                        return String.format(Locale.FRANCE, "%02d:%02d", secondes, cent_secondes);
                    }
                }

            case STATIC_DISPLAY:
                return String.format(Locale.FRANCE, "%02d:%02d", minutes, secondes);

            default:
                return "";
        }
    }

}

