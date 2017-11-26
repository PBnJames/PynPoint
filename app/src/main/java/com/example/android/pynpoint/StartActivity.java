package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, StartActivity.class);
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
        setContentView(R.layout.activity_start);
        if(mPrefs.getBoolean("description_read",false)){
            toTimerSetActivity();
        } else {
            toDescriptionActivity();
        }
    }

    public void toDescriptionActivity() {
        Button startScreenButton = (Button)findViewById(R.id.start_screen_button);
        final Intent descriptionIntent = DescriptionActivity.buildIntent(this);
        startScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(descriptionIntent);
                finish();
            }
        });
    }

    public void toTimerSetActivity() {
        Button startScreenButton = (Button)findViewById(R.id.start_screen_button);
        final Intent timerSetIntent = TimerSetActivity.buildIntent(this);
        startScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(timerSetIntent);
                finish();
            }
        });
    }


}
