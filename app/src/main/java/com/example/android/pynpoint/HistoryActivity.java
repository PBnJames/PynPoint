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
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class HistoryActivity extends AppCompatActivity {
    private AppDatabase database;
    private User user;
    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, HistoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setContentView(R.layout.activity_history);

        if (mPrefs.getBoolean("purple", false)) {
            setTheme(R.style.ActivityTheme_Primary_Base_Purple);
        } else if(mPrefs.getBoolean("green", false)){
            setTheme(R.style.ActivityTheme_Primary_Base_Green);
        }

        database = AppDatabase.getDatabase(getApplicationContext());

        List<User> users = database.userDao().getAllUser();


        user = database.userDao().getAllUser().get(0);

        List<User> user = database.userDao().getAllUser();



        Log.d("debug", "checkpoint8"+user.size());

        TextView Date1 = findViewById(R.id.row_1_date);
        String text= " " + user.get(0).date;
        Date1.setText(text);
        TextView length1 = findViewById(R.id.row_1_length);
        String text2= user.get(0).length+"";
        length1.setText(text2);
        TextView comp1 = findViewById(R.id.row_1_percent_complete);
        String text3=  user.get(0).complete;
        comp1.setText(text3);
        TextView points1 = findViewById(R.id.row_1_points);
        String text4= user.get(0).points ;
        points1.setText(text4);
        int x=user.size();
        if(user.size()>1)
        {

            TextView Date12 = findViewById(R.id.row_2_date);
            String text12 = user.get(1).date + " ";
            Date12.setText(text12);
            TextView length12 = findViewById(R.id.row_2_length);
            String text22 = user.get(1).length;
            length12.setText(text22);
            TextView comp12 = findViewById(R.id.row_2_percent_complete);
            String text32 = user.get(1).complete;
            comp12.setText(text32);
            TextView points12 = findViewById(R.id.row_2_points);
            String text42 = user.get(1).points;
            points12.setText(text42);
        }
        if(user.size()>2) {
            TextView Date13 = findViewById(R.id.row_3_date);
            String text13 = user.get(2).date + " ";
            Date13.setText(text13);
            TextView length13 = findViewById(R.id.row_3_length);
            String text23 = user.get(2).length;
            length13.setText(text23);
            TextView comp13 = findViewById(R.id.row_3_percent_complete);
            String text33 = user.get(2).complete;
            comp13.setText(text33);
            TextView points13 = findViewById(R.id.row_3_points);
            String text43 = user.get(2).points;
            points13.setText(text43);
        }
        if(user.size()>3)
        {
            TextView Date14 = findViewById(R.id.row_4_date);
            String text14 = user.get(3).date + " ";
            Date14.setText(text14);
            TextView length14 = findViewById(R.id.row_4_length);
            String text24 = user.get(3).length + " ";
            length14.setText(text24);
            TextView comp14 = findViewById(R.id.row_4_percent_complete);
            String text34 = user.get(3).complete;
            comp14.setText(text34);
            TextView points14 = findViewById(R.id.row_4_points);
            String text44 = user.get(3).points;
            points14.setText(text44);
        }






        super.onCreate(savedInstanceState);

        toProfileActivity();
        toSettingsActivity();
        toTimerSetActivity();
    }

    public void toProfileActivity() {
        Button profileScreenButton = (Button)findViewById(R.id.to_profile_button_history);
        final Intent profileIntent = ProfileActivity.buildIntent(this);
        profileScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(profileIntent);
                finish();
            }
        });
    }

    public void toSettingsActivity() {
        Button settingsScreenButton = (Button)findViewById(R.id.to_settings_button_history);
        final Intent settingsIntent = SettingsActivity.buildIntent(this);
        settingsScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settingsIntent);
                finish();
            }
        });
    }

    public void toTimerSetActivity() {
        Button timerScreenButton = (Button)findViewById(R.id.to_timer_button_history);
        final Intent setTimerIntent = TimerSetActivity.buildIntent(this);
        timerScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(setTimerIntent);
                finish();
            }
        });
    }

}
