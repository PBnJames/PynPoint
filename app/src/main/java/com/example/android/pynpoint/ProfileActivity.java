package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private AppDatabase database;
    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, ProfileActivity.class);
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
        } else if(mPrefs.getBoolean("red", false)){
            setTheme(R.style.ActivityTheme_Primary_Base_Red);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        database = AppDatabase.getDatabase(getApplicationContext());

        List<User> user = database.userDao().getAllUser();
        int size=user.size();
        Log.d("debug", "checkpoint79" + user.size());
        if(user.size()!=0)
        {
            int completed = 0;
            int points = 0;
            int hours = 0;
            int minutes = 0;
            for (int i = 0; i < size; i++)//points, complete, time,  numunm
            {

                    completed= completed+Integer.parseInt(user.get(i).complete);
                    int tacobell=Integer.parseInt(user.get(i).points)*(Integer.parseInt(user.get(i).complete));
                    points = points + (tacobell/100);

                hours = hours+  Integer.parseInt(user.get(size-1).length.substring(0, 1).replaceAll("\\s", ""));
                minutes = minutes+ Integer.parseInt(user.get(size-1).length.substring(4,6).replaceAll("\\s", ""));
                Log.d("debug", "checkpoint79   " + Integer.parseInt(user.get(i).points)+"   "+Integer.parseInt(user.get(i).complete) +"");

            }




            TextView points1 = findViewById(R.id.user_points);
            points1.setText("" + points);


        TextView perc = findViewById(R.id.overall_percentage_complete_value);
        perc.setText(""+(double)(size-completed)/size);


            TextView perc5 = findViewById(R.id.overall_percentage_complete_value);
            perc5.setText("" + (100 - ((size*100) - completed) / (size)));

            TextView tacos = findViewById(R.id.overall_time_studied_value);
            tacos.setText("" + points);


            TextView killme = findViewById(R.id.num_of_study_sessions_value);
            killme.setText(size + "");





        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt("points", points);
        editor.apply();
    }

        toShopActivity();
        toHistoryActivity();
        toTimerSetActivity();
        toSettingsActivity();
    }

    public void toShopActivity() {
        Button shopScreenButton = (Button)findViewById(R.id.shop_button);
        final Intent shopIntent = ShopActivity.buildIntent(this);
        shopScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(shopIntent);
                finish();
            }
        });
    }


    public void toHistoryActivity() {
        Button historyScreenButton = (Button)findViewById(R.id.to_history_button_profile);
        final Intent historyIntent = HistoryActivity.buildIntent(this);
        historyScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(historyIntent);
                finish();
            }
        });
    }


    public void toTimerSetActivity() {
        Button timerScreenButton = (Button)findViewById(R.id.to_timer_button_profile);
        final Intent setTimerIntent = TimerSetActivity.buildIntent(this);
        timerScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(setTimerIntent);
                finish();
            }
        });
    }

    public void toSettingsActivity() {
        Button settingsScreenButton = (Button)findViewById(R.id.to_settings_button_profile);
        final Intent settingsIntent = SettingsActivity.buildIntent(this);
        settingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settingsIntent);
                finish();
            }
        });
    }
}
