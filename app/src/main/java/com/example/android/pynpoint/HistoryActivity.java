package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private TextView mTextView;

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, HistoryActivity.class);
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
        setContentView(R.layout.activity_history);

        if(mPrefs.getBoolean("green", false)){
            setTableGreenColor();
        } else if(mPrefs.getBoolean("red", false)){
            setTableRedColor();
        }


        toProfileActivity();
        toSettingsActivity();
        toTimerSetActivity();
    }

    public void setTableGreenColor() {
        mTextView = (TextView) findViewById(R.id.row0_date_title);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row0_length_title);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row0_percent_complete_title);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row0_points_title);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));

        mTextView = (TextView) findViewById(R.id.row_1_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_1_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_1_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_1_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));

        mTextView = (TextView) findViewById(R.id.row_2_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_2_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_2_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_2_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));

        mTextView = (TextView) findViewById(R.id.row_3_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_3_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_3_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_3_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));

        mTextView = (TextView) findViewById(R.id.row_4_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_4_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_4_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_4_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_ugly_green));

        mTextView = (TextView) findViewById(R.id.row_5_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_5_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_5_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
        mTextView = (TextView) findViewById(R.id.row_5_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_ugly_green));
    }

    public void setTableRedColor() {
        mTextView = (TextView) findViewById(R.id.row0_date_title);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row0_length_title);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row0_percent_complete_title);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row0_points_title);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));

        mTextView = (TextView) findViewById(R.id.row_1_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_1_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_1_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_1_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));

        mTextView = (TextView) findViewById(R.id.row_2_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_2_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_2_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_2_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));

        mTextView = (TextView) findViewById(R.id.row_3_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_3_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_3_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_3_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));

        mTextView = (TextView) findViewById(R.id.row_4_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_4_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_4_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_4_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_darker_crimson_red));

        mTextView = (TextView) findViewById(R.id.row_5_date);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_5_length);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_5_percent_complete);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
        mTextView = (TextView) findViewById(R.id.row_5_points);
        mTextView.setBackground(ContextCompat.getDrawable(this, R.drawable.cell_shape_lighter_crimson_red));
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
