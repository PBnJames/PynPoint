package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TimerSetActivity extends AppCompatActivity {

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, TimerSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_set);
        setSpinners(R.id.timer_hour_spinner, 17);
        setSpinners(R.id.timer_minute_spinner, 60);
        setSpinners(R.id.break_hour_spinner, 17);
        setSpinners(R.id.break_minute_spinner, 60);
        setSpinners(R.id.interval_hour_spinner, 17);
        setSpinners(R.id.interval_minute_spinner, 60);
        toTimerStartActivity();
        toSettingsActivity();
        toHistoryActivity();
        toProfileActivity();
    }

    public void toProfileActivity() {
        Button profileScreenButton = (Button)findViewById(R.id.to_profile_button);
        final Intent profileIntent = ProfileActivity.buildIntent(this);
        profileScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
                finish();
            }
        });
    }

    public void toHistoryActivity() {
        Button historyScreenButton = (Button)findViewById(R.id.to_history_button);
        final Intent historyIntent = HistoryActivity.buildIntent(this);
        historyScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(historyIntent);
                finish();
            }
        });
    }

    public void toSettingsActivity() {
        Button settingsScreenButton = (Button)findViewById(R.id.to_settings_button);
        final Intent settingsIntent = SettingsActivity.buildIntent(this);
        settingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settingsIntent);
                finish();
            }
        });
    }

    public void toTimerStartActivity() {
        Button startScreenButton = (Button)findViewById(R.id.start_timer_button);
        final Intent timerStartIntent = TimerStartActivity.buildIntent(this);
        startScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(timerStartIntent);
                finish();
            }
        });
    }

    public void setSpinners(int spinnerId, int numOfItems) {
        Spinner hourSpinner=(Spinner) findViewById(spinnerId);
        Integer[] items = new Integer[numOfItems];
        for(int i = 0; i < items.length; ++i){
            items[i] = i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        hourSpinner.setAdapter(adapter);
    }




}
