package com.example.android.pynpoint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        toTimerSetActivity();
    }

    private void toTimerSetActivity() {
        Button descriptionScreenButton = (Button)findViewById(R.id.description_screen_button);

        descriptionScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DescriptionActivity.this, TimerSetActivity.class));
            }
        });
    }
}
