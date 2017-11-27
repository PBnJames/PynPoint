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
import java.util.Date;
import java.util.concurrent.TimeUnit;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;

import java.util.List;


public class TimerStartActivity extends AppCompatActivity {

    private User user;
    private AppDatabase database;

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
        } else if(mPrefs.getBoolean("red", false)){
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_start);

        database = AppDatabase.getDatabase(getApplicationContext());

        //declares textboxes and makes them editable

        final TextView intervalTime = (TextView) findViewById(R.id.intervalTime);
        final TextView currentText = (TextView) findViewById(R.id.currentTask);
        final TextView totalTimeLeft = (TextView) findViewById(R.id.totalTimeLeft);

        //draws values from spinner data see TimerSetActivity
        Bundle bundle = getIntent().getExtras();
        final int timerMinutes = Integer.parseInt(bundle.getString("TM"));
        final int timerHours = Integer.parseInt(bundle.getString("TH"));
        final int intervalMinutes = Integer.parseInt(bundle.getString("IM"));
        final int intervalHours = Integer.parseInt(bundle.getString("IH"));
        final int breakMinutes = Integer.parseInt(bundle.getString("BM"));
        final int breakHours = Integer.parseInt(bundle.getString("BH"));

        //Log.d("debug", "checkpoint7");//debug
        //database.userDao().removeAllUsers();
        //Conversion to miliseconds from respective times
        final int timer = (60000 * (timerMinutes)) + ((timerHours * 3600000));
        final int breaks = (60000 * (breakMinutes)) + ((breakHours * 3600000));
        final int interval = (60000 * (intervalMinutes)) + ((intervalHours * 3600000));


        if(breaks!=0 ||interval!=0)
        {
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








            // add some data
            List<User> users = database.userDao().getAllUser();
            Date currentTime = Calendar.getInstance().getTime();
            final String date=""+ currentTime;



                int x = users.size() + 1;
                final int y=x;

                List<User> user = database.userDao().getAllUser();

                /*database.userDao().removeAllUsers();
                //false data
                database.userDao().addUser(new User(0, ""+1 + " h " + 20 + " m", "" + "date", "20", "" + "80"));
                database.userDao().addUser(new User(2, ""+2 + " h " + 30 + " m", "" + "date", "100", "" + "60"));
                database.userDao().addUser(new User(1, ""+3 + " h " + 0 + " m", "" + "date", "60", "" + "120"));
                */
                database.userDao().addUser(new User(x, timerHours + " h " + timerMinutes + " m", "" + date.substring(4, 11) + date.substring(date.length() - 4, date.length()), "X", "" + ((interval * totalLength) + intervalRemainder) / 60000));

               // User user2 = new User(i,"24234","","12312","123");
               // database.userDao().addUser(user2);
               // database.userDao().addUser(new User(x+1, timerHours + " h " + timerMinutes + " z", "" + date.substring(4, 11) + date.substring(date.length() - 4, date.length()), "X", "" + ((interval * totalLength) + intervalRemainder) / 1000));
                List<User> user23 = database.userDao().getAllUser();
                Log.d("debug", "checkpoint7" + user23.size());



            {
                //default starts with study interval declared here first to start.
                currentText.setText("Study Time!");

               // Log.d("debug", "checkpoint8"+date.substring(4,11)+date.substring(date.length()-4,date.length()));//debug
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
             //server updates
                int calculator=timer/100;

            new CountDownTimer(timer, calculator)
            {
                int l=0;
                public void onTick(long millisUntilFinished)
                {
                    int z=y;
                    l++;
                    List<User> user = database.userDao().getAllUser();
                    database.userDao().addUser(new User(z, timerHours + " h " + timerMinutes + " m", "" + date.substring(4, 11) + date.substring(date.length() - 4, date.length()), l+"", "" + ((interval * totalLength) + intervalRemainder) / 60000));

                }


                public void onFinish()
                {


                }
            }.start();





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

                                    public void onFinish() {
                                        currentText.setText("Done Studying!"); //finishing message
                                        intervalTime.setText("Hours:0 Minutes:0 Seconds:0");     // add anything that needs to be added here. IE send intent/ update server ect.
                                        toTimerSetActivity();

                                        List<User> user = database.userDao().getAllUser();
                                        database.userDao().addUser(new User(y, timerHours + " h " + timerMinutes + " m", "" + date.substring(4, 11) + date.substring(date.length() - 4, date.length()), "100", "" + ((interval * totalLength) + intervalRemainder) / 60000));
                                        toResultsActivity();




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
        }
        else
            {
                toTimerSetActivity();
            }


    }
    private void updateData(int I)
    {
        List<User> user = database.userDao().getAllUser();
        List<Table> trophiesForUser = database.trophyDao().findTrophiesForUser(user.get(I).id);
        TextView textView2 = findViewById(R.id.result);


    }

    public void toTimerSetActivity() {

        final Intent timerSetIntent = TimerSetActivity.buildIntent(this);
                startActivity(timerSetIntent);
                finish();
    }

    public void toResultsActivity()
    {

        final Intent ResultsIntent = ResultsActivity.buildIntent(this);
                startActivity(ResultsIntent);
                finish();
    }





    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
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

