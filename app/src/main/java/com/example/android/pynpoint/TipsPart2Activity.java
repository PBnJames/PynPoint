package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipsPart2Activity extends AppCompatActivity {

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, TipsPart2Activity.class);
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
        setContentView(R.layout.activity_tips_part2);
        toTip3Activity();
    }

    public void toTip3Activity() {
        Button tip2Button = (Button)findViewById(R.id.next_tip_button_tip2);
        final Intent tip3Intent = TipsPart3Activity.buildIntent(this);

        tip2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(tip3Intent);
                finish();
            }
        });
    }
}
