package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.lang.*;
import java.lang.*;


public class TimerSetActivity extends AppCompatActivity {

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, TimerSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (mPrefs.getBoolean("purple", false)) {
            setTheme(R.style.ActivityTheme_Primary_Base_Purple);
        } else if(mPrefs.getBoolean("green", false)){
            setTheme(R.style.ActivityTheme_Primary_Base_Green);
        }
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
    public void toProfileActivity()
    {
        Button profileScreenButton = (Button)findViewById(R.id.to_profile_button);
        final Intent profileIntent = ProfileActivity.buildIntent(this);
        profileScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
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



        Spinner mySpinner=(Spinner) findViewById(R.id.timer_hour_spinner);
        final String timerhour = mySpinner.getSelectedItem().toString();
        Log.d("myTag", "This is my miho55essage"+timerhour);

        startScreenButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //creates strings that store spinner data after button has been clicked
                Spinner THSpinner=(Spinner) findViewById(R.id.timer_hour_spinner);
                String TH = THSpinner.getSelectedItem().toString();

                Spinner TMSpinner=(Spinner) findViewById(R.id.timer_minute_spinner);
                String TM = TMSpinner.getSelectedItem().toString();

                Spinner IHSpinner=(Spinner) findViewById(R.id.interval_hour_spinner);
                String IH = IHSpinner.getSelectedItem().toString();

                Spinner IMSpinner=(Spinner) findViewById(R.id.interval_minute_spinner);
                String IM = IMSpinner.getSelectedItem().toString();

                Spinner BHSpinner=(Spinner) findViewById(R.id.break_hour_spinner);
                String BH = BHSpinner.getSelectedItem().toString();

                Spinner BMSpinner=(Spinner) findViewById(R.id.break_minute_spinner);
                String BM = BMSpinner.getSelectedItem().toString();

                //  Log.d("myTag", ""+TH+""+TM+""+IH+""+IM+""+BH+""+BM);
                // log for debug purposes

                timerStartIntent.putExtra("TH",TH);
                timerStartIntent.putExtra("TM",TM);
                timerStartIntent.putExtra("BH",BH);
                timerStartIntent.putExtra("BM",BM);
                timerStartIntent.putExtra("IM",IM);
                timerStartIntent.putExtra("IH",IH); //adds extra data to intent
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
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        hourSpinner.setAdapter(adapter);
    }




}
