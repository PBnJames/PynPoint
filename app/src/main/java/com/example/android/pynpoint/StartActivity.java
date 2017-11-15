package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
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

        if (App.getInstance().isPurpleEnabled()) {
            setTheme(R.style.ActivityTheme_Primary_Base_Purple);
        } else if(App.getInstance().isGreenEnabled()){
            setTheme(R.style.ActivityTheme_Primary_Base_Green);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        toDescriptionActivity();
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


}
