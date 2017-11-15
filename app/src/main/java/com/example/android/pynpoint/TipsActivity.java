package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TipsActivity extends AppCompatActivity {

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, TipsActivity.class);
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
        setContentView(R.layout.activity_tips);
    }
}
