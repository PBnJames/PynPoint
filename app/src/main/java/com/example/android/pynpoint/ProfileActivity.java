package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private AppDatabase database;
    private ImageView mImageView;
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

        mImageView = (ImageView) findViewById(R.id.profile_image);

        if(mPrefs.getBoolean("cat", false)){
            mImageView.setImageResource(R.drawable.cathead);
        }else{
            mImageView.setImageResource(R.drawable.doghead);
        }

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




            TextView pointsTextView = findViewById(R.id.user_points);
            pointsTextView.setText("" + points);

            TextView overallPercentageCompleteTextView = findViewById(R.id.overall_percentage_complete_value);
            overallPercentageCompleteTextView.setText("" + (100 - ((size*100) - completed) / (size)));

            TextView overallTimeStudiedTextView = findViewById(R.id.overall_time_studied_value);
            overallTimeStudiedTextView.setText("" + points);


            TextView numOfStudySessionsTextView = findViewById(R.id.num_of_study_sessions_value);
            numOfStudySessionsTextView.setText(size + "");





        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt("points", points);
        editor.putInt("overallpercentage", (100 - ((size*100) - completed) / (size)));
        editor.apply();
    }

        PieChart chart = (PieChart) findViewById(R.id.pie_chart);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(getResources().getColor(R.color.antiFlashWhite));
        chart.setHoleRadius(15f);
        chart.setTransparentCircleRadius(20f);
        Legend l = chart.getLegend();
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        chart.setEntryLabelColor(getResources().getColor(R.color.black));
        chart.setEntryLabelTextSize(12f);
        ArrayList<PieEntry> PieEntry = new ArrayList<>();
        int percentValue = mPrefs.getInt("overallpercentage", 0);
        PieEntry.add(new PieEntry(percentValue, "% Complete"));
        PieEntry.add(new PieEntry(100-percentValue, "% Incomplete"));

        PieDataSet dataSet = new PieDataSet(PieEntry, "");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);


        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);
        chart.setData(data);





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
