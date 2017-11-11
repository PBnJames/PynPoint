package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TimerStartActivity extends AppCompatActivity {

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, TimerStartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_start);
    }
}
