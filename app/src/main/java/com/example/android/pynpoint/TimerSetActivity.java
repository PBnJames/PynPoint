package com.example.android.pynpoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TimerSetActivity extends AppCompatActivity {

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
        final Intent intent = new Intent(this, ProfileActivity.class);
        profileScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    public void toHistoryActivity() {
        Button historyScreenButton = (Button)findViewById(R.id.to_history_button);
        final Intent intent = new Intent(this, HistoryActivity.class);
        historyScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    public void toSettingsActivity() {
        Button settingsScreenButton = (Button)findViewById(R.id.to_settings_button);
        final Intent intent = new Intent(this, SettingsActivity.class);
        settingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    public void toTimerStartActivity() {
        Button startScreenButton = (Button)findViewById(R.id.start_timer_button);
        final Intent intent = new Intent(this, TimerStartActivity.class);
        startScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
