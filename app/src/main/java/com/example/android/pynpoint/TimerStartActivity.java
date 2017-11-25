package com.example.android.pynpoint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.*;
import java.util.concurrent.TimeUnit;
import android.os.CountDownTimer;
import android.widget.TextView;


public class TimerStartActivity extends AppCompatActivity {

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, TimerStartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (mPrefs.getBoolean("purple", false)) {
            setTheme(R.style.ActivityTheme_Primary_Base_Purple);
        } else if (mPrefs.getBoolean("green", false)) {
            setTheme(R.style.ActivityTheme_Primary_Base_Green);
        }
        super.onCreate(savedInstanceState);

        //declares textboxes and makes them editable
        setContentView(R.layout.activity_timer_start);
        final TextView intervalTime = (TextView) findViewById(R.id.intervalTime);
        final TextView currentText = (TextView) findViewById(R.id.currentTask);
        final TextView totalTimeLeft = (TextView) findViewById(R.id.totalTimeLeft);

        //draws values from spinner data see TimerSetActivity
        Bundle bundle = getIntent().getExtras();
        int timerMinutes = Integer.parseInt(bundle.getString("TM"));
        int timerHours = Integer.parseInt(bundle.getString("TH"));
        int intervalMinutes = Integer.parseInt(bundle.getString("IM"));
        int intervalHours = Integer.parseInt(bundle.getString("IH"));
        int breakMinutes = Integer.parseInt(bundle.getString("BM"));
        int breakHours = Integer.parseInt(bundle.getString("BH"));

        //Log.d("debug", "checkpoint7");//debug

        //Conversion to miliseconds from respective times
        final int timer = (60000 * (timerMinutes)) + ((timerHours * 3600000));
        final int breaks = (60000 * (breakMinutes)) + ((breakHours * 3600000));
        final int interval = (60000 * (intervalMinutes)) + ((intervalHours * 3600000));

        //default starts with study interval declared here first to start.
        currentText.setText("Study Time!");


        //totalTimeLeft Timer. The timers run in parallel. IE totalTimeleft runs at the same time the others do as well.
        new CountDownTimer(timer, 1000) //timer is total timer, 1000 is the countdown tick. IE settext updates every second
        {
            public void onTick(long millisUntilFinished) {
                //Calculations to display hours and minutes. Can also include seconds, but I opted out. Can easily readd.
                totalTimeLeft.setText("Hours:" + TimeUnit.MILLISECONDS.toHours(millisUntilFinished) + "   Minutes:" + (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))));
            }

            //is called when timer is done
            public void onFinish() {
                // timer defaults to one rather than 0. Added a 0 for more plesant look.
                totalTimeLeft.setText("Hours:0   Minutes:0 ");
            }
        }.start();


        //Algorithm to determine the timer lengths. declared as final to be able to use in other methods.
        final int intervalRemainder, breakRemainder;
        final int totalLength = timer / (breaks + interval); //used to find out how many loops of "study and break" there are
        final int remainder = timer - (totalLength * (breaks + interval)); //find the leftover time.

        //tests to see if final interval needs to include breaks and sets the respective remainder values.
        if (remainder - interval <= 0) {
            intervalRemainder = remainder;
            breakRemainder = 0;
        } else {
            intervalRemainder = interval;
            breakRemainder = remainder - interval;
        }


        new CountDownTimer(interval, 1000) {
            int counter = 1; //its one as its only does counter++ after the first iteration.

            public void onTick(long millisUntilFinished) //outside timer. iterates through an interval. Includes milliseconds once again can be added or removed.
            {
                intervalTime.setText("Hours:" + TimeUnit.MILLISECONDS.toHours(millisUntilFinished) + "   Minutes:" + (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + "   Seconds:" +
                        (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                currentText.setText("Break Time!"); //changes textbox to indicate what current cycle is.

                new CountDownTimer(breaks, 1000) {
                    public void onTick(long millisUntilFinished)  //Secondary nestled timer. This timer is for the breaks and only starts once the study interval ends. Also includes milliseconds
                    {
                        intervalTime.setText("Hours:" + TimeUnit.MILLISECONDS.toHours(millisUntilFinished) + "   Minutes:" + (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + "   Seconds:" +
                                (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    }

                    public void onFinish() {
                        currentText.setText("Work Time!");//changes textbox to indicate what current cycle is.

                        }
                    }.start();


                new Handler().postDelayed(new Runnable()  //alternative to thread.sleep() doesnt cause blackscreen. This small bit was by far the most time consuming.
                {

                    @Override
                    public void run() {
                    if (counter < totalLength)  //checks to see if it needs to loop again.
                    {
                        counter++;  //modifies a local variable so variable doesnt have to be final.
                        start();    // causes it to be looped. As 'for' loops would run both timers at the same time, and a method not in the "on create" wouldnt alter the textboxes.

                        }
                    }
                }, (breaks)); //delays the start of the next cycle until the break is over. IE its running a delay parallel to the other timers to sync them correctly.

            }
        }.start();


        // this is another delay that runs parallel Similar to the one above, but this is for the "time remainder" and doesnt have a pseudo loop.
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                new CountDownTimer(intervalRemainder, 1000) {
                    public void onTick(long millisUntilFinished) {
                        intervalTime.setText("Hours:" + TimeUnit.MILLISECONDS.toHours(millisUntilFinished) + "   Minutes:" + (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + "   Seconds:" +
                                (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                    }

                    public void onFinish() {

                        new CountDownTimer(breakRemainder, 1000) {
                            public void onTick(long millisUntilFinished) {
                                intervalTime.setText("Hours:" + TimeUnit.MILLISECONDS.toHours(millisUntilFinished) + " Minutes:" + (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + ":" +
                                        (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish()
                            {
                                currentText.setText("Done Studying!"); //finishing message
                                intervalTime.setText("Hours:0 Minutes:0 Seconds:0");     // add anything that needs to be added here. IE send intent/ update server ect.

                                //need to debug bottom part more. used to create an alert that the timer has gone off.
                                /*runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        if (!isFinishing()){
                                            new AlertDialog.Builder(YourActivity.this)
                                                    .setTitle("Times Up!")
                                                    .setMessage("Time to:")
                                                    .setCancelable(false)
                                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                        }
                                                    }).show();
                                        }
                                    }
                                });*/
                            }

                        }.start();

                    }
                }.start();
            }
        }, timer - intervalRemainder - breakRemainder); //delays the whole thing to sync with other timers.




    }
    protected void onDestroy(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_timer_start);
        final TextView totalTimeLeft = (TextView) findViewById(R.id.totalTimeLeft);

        int leftovertime=Integer.parseInt(totalTimeLeft.toString()); // need to parse the string to seperate but it shows remaining time.

        Bundle bundle = getIntent().getExtras();
        int timerMinutes = Integer.parseInt(bundle.getString("TM"));
        int timerHours= Integer.parseInt(bundle.getString("TH"));
        final int timer = (60000 * (timerMinutes)) + ((timerHours * 3600000));
        int percentageCompleted = (timer -leftovertime)/timer;
        //update sql server here in case it leaves.


    }


}

