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
    }

    private void toTimerStartActivity() {
        Button descriptionScreenButton = (Button)findViewById(R.id.start_timer_button);

        descriptionScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimerSetActivity.this, TimerStartActivity.class));
            }
        });
    }

    private void setSpinners(int spinnerId, int numOfItems) {
        Spinner hourSpinner=(Spinner) findViewById(spinnerId);
        Integer[] items = new Integer[numOfItems];
        for(int i = 0; i < items.length; ++i){
            items[i] = i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        hourSpinner.setAdapter(adapter);
    }




}
