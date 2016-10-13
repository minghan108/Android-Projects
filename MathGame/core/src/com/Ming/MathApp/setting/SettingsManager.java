package com.Ming.MathApp.setting;

/**
 * Created by MingHan on 9/4/2015.
 */
public class SettingsManager {
    //this class uses the the singleton technique
    private int score = 0;
    static SettingsManager instance = null;

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public int getScore() {
        return score;
    }

    public String getStringScore() {
        return score + "";
    }

    public void increaseScore() {
        score++;
    }

    public void resetScore() {
        score = 0;
    }
}
