package com.example.android.pynpoint;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class App extends Application {
    public static final String TAG = "App";

    private boolean isPurpleEnabled = true;
    private boolean isGreenEnabled = false;

    public static App instance = null;

    public App() {}

    public static App getInstance() {
        if (instance == null) {
                if (instance == null) {
                    instance = new App();
                }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences mPrefs =  PreferenceManager.getDefaultSharedPreferences(this);
        this.isPurpleEnabled = mPrefs.getBoolean("purple", true);
        this.isGreenEnabled = mPrefs.getBoolean("green", false);

    }

    public boolean isPurpleEnabled() {
        return isPurpleEnabled;
    }

    public void setIsPurpleEnabled(boolean isPurpleEnabled) {
        this.isPurpleEnabled = isPurpleEnabled;
    }

    public boolean isGreenEnabled() {
        return isGreenEnabled;
    }

    public void setIsGreenEnabled(boolean isGreenEnabled) {
        this.isGreenEnabled = isGreenEnabled;
    }
}