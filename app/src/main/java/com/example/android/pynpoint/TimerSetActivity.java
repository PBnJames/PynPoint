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
        setHourSpinner();
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

    private void setHourSpinner() {
        Spinner hour_spinner=(Spinner) findViewById(R.id.timer_hour_spinner);
        Integer[] items = new Integer[17];
        for(int i = 0; i < items.length; ++i){
            items[i] = i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        hour_spinner.setAdapter(adapter);
    }


}
