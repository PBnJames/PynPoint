package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DescriptionActivity extends AppCompatActivity {

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, DescriptionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        toTimerSetActivity();
    }

    public void toTimerSetActivity() {
        Button descriptionScreenButton = (Button)findViewById(R.id.description_screen_button);
        final Intent setTimerIntent = TimerSetActivity.buildIntent(this);

        descriptionScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(setTimerIntent);
                finish();
            }
        });
    }
}
