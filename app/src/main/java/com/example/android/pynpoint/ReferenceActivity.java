package com.example.android.pynpoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        toProfileActivity();
        toHistoryActivity();
        toSettingsActivity();
        toTimerSetActivity();
    }

    public void toProfileActivity() {
        Button profileScreenButton = (Button)findViewById(R.id.to_profile_button_reference);
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
        Button historyScreenButton = (Button)findViewById(R.id.to_history_button_reference);
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


    public void toTimerSetActivity() {
        Button timerScreenButton = (Button)findViewById(R.id.to_timer_button_reference);
        final Intent intent = new Intent(this, TimerSetActivity.class);
        timerScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    public void toSettingsActivity() {
        Button settingsScreenButton = (Button)findViewById(R.id.to_settings_button_reference);
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
}
