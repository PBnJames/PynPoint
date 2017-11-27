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

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private AppDatabase database;
    public static Intent buildIntent(Context context){

        Intent intent = new Intent(context, ResultsActivity.class);
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
        setContentView(R.layout.activity_results);


        database = AppDatabase.getDatabase(getApplicationContext());

        /*database.userDao().removeAllUsers();

        database.userDao().addUser(new User(0, ""+1 + " h " + 20 + " m", "" + "date", "20", "" + "80"));
        database.userDao().addUser(new User(2, ""+2 + " h " + 30 + " m", "" + "date", "100", "" + "60"));
        database.userDao().addUser(new User(1, ""+3 + " h " + 0 + " m", "" + "date", "60", "" + "120"));
*/

        final TextView timeS = (TextView) findViewById(R.id.time_studied_default);
        final TextView btime = (TextView) findViewById(R.id.break_time_default);
        final TextView ttime = (TextView) findViewById(R.id.total_time_default);
        final TextView cPoints = (TextView) findViewById(R.id.points_current_default);
        final TextView Pearned = (TextView) findViewById(R.id.points_earned_default);
        final TextView total = (TextView) findViewById(R.id.points_total_default);

        database = AppDatabase.getDatabase(getApplicationContext());
        List<User> user = database.userDao().getAllUser();

        int size=user.size();

        int completed = 0;
        int points = 0;
        int hours = 0;
        int minutes = 0;
        int math=0;

        for (int i = 0; i < size; i++)//points, complete, time,  numunm
        {

                int g= Integer.parseInt(user.get(i).points);
                int y= Integer.parseInt(user.get(i).complete);
                math= g*100;
                math=(math*y)/10000;
                points = (points + math);

               /* hours = hours + Integer.parseInt(user.get(i).length.substring(0, 1).replaceAll("\\s", ""));
                int measure = user.get(i).length.length();
                minutes = minutes + Integer.parseInt(user.get(i).length.substring(4,6).replaceAll("\\s", ""));
                */


                Log.d("debug", "f"+hours+"  "+minutes+" "+user.get(i).length.substring(4,6).replaceAll("\\s", ""));

        }

        hours =  Integer.parseInt(user.get(size-1).length.substring(0, 1).replaceAll("\\s", ""));

        minutes = Integer.parseInt(user.get(size-1).length.substring(4,6).replaceAll("\\s", ""));

        int g= Integer.parseInt(user.get(user.size()-1).points);
        int y= Integer.parseInt(user.get(user.size()-1).complete);
        math= g*100;
        math=(math*y)/10000;

        Log.d("debug", "f"+g+"hours"+hours+" "+minutes);


        int bminutes=(hours*60)+minutes-(g);
        int reduce=bminutes/60;
        int bbminutes=bminutes%60;

        int tstudyh=Integer.parseInt(user.get(size-1).points)/60;
        int tstudym=Integer.parseInt(user.get(size-1).points)%60;



        total.setText(""+points);
        int cpoints=points-math;

        btime.setText(reduce+" h "+bbminutes+" m");
        timeS.setText(tstudyh+" h "+tstudym+" m");
        cPoints.setText(""+cpoints);
        Pearned.setText(""+math);


        ttime.setText(""+user.get(size-1).length);



        toProfileActivity();
        toHistoryActivity();
        toTimerSetActivity();
        toSettingsActivity();
    }












    public void toProfileActivity() {
        Button profileScreenButton = (Button)findViewById(R.id.to_profile_button_results);
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
        Button historyScreenButton = (Button)findViewById(R.id.to_history_button_results);
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
        Button timerScreenButton = (Button)findViewById(R.id.to_timer_button_results);
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
        Button settingsScreenButton = (Button)findViewById(R.id.to_settings_button_results);
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
