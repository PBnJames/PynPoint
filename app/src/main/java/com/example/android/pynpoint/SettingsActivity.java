package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends PreferenceActivity {

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (App.getInstance().isPurpleEnabled()) {
            setTheme(R.style.ActivityTheme_Primary_Base_Purple);
        } else if(App.getInstance().isGreenEnabled()){
            setTheme(R.style.ActivityTheme_Primary_Base_Green);
        }
        super.onCreate(savedInstanceState);
       // setTheme(R.style.PreferenceScreen);
        addPreferencesFromResource(R.xml.pref_app);
        setContentView(R.layout.activity_settings_button_layout);
        toAboutActivity();
        toReferenceActivity();
        toProfileActivity();
        toHistoryActivity();
        toTimerSetActivity();
        toTipsActivity();
    }

    public void toReferenceActivity() {
        Preference aboutScreenButton = findPreference(getString(R.string.reference_button));
        final Intent referenceIntent = ReferenceActivity.buildIntent(this);
        aboutScreenButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(referenceIntent);
                finish();
                return true;
            }
        });
    }

    public void toTipsActivity() {
        Preference aboutScreenButton = findPreference(getString(R.string.tips_button));
        final Intent tipsIntent = TipsActivity.buildIntent(this);
        aboutScreenButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(tipsIntent);
                finish();
                return true;
            }
        });
    }


    public void toAboutActivity() {
        Preference aboutScreenButton = findPreference(getString(R.string.about_button));
        final Intent aboutIntent = AboutActivity.buildIntent(this);
        aboutScreenButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(aboutIntent);
                finish();
                return true;
            }
        });
    }


    public void toProfileActivity() {
        Button profileScreenButton = (Button)findViewById(R.id.to_profile_button_settings);
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
        Button historyScreenButton = (Button)findViewById(R.id.to_history_button_settings);
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
        Button timerScreenButton = (Button)findViewById(R.id.to_timer_button_settings);
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
