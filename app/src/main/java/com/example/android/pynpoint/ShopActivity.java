package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    private ListView mListView;

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, ShopActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mListView = (ListView) findViewById(R.id.shop_list_view);

        ShopItem row1 = createShopItem("test1", "test2", "test3", getResources().getDrawable(R.drawable.doghead));
        ShopItem row2 = createShopItem("test1", "test2", "test3", getResources().getDrawable(R.drawable.cathead));

        final ArrayList<ShopItem> shopList = new ArrayList<ShopItem>();
        shopList.add(row1);
        shopList.add(row2);


        ShopAdapter adapter = new ShopAdapter(this, shopList);
        mListView.setAdapter(adapter);




        toTimerSetActivity();
        toSettingsActivity();
        toHistoryActivity();
        toProfileActivity();
    }

    public ShopItem createShopItem(String itemTitle, String itemDescription, String itemLabel, Drawable itemImage ){
        ShopItem newShopItem = new ShopItem();
        newShopItem.title = itemTitle;
        newShopItem.description = itemDescription;
        newShopItem.label = itemLabel;
        newShopItem.image = itemImage;

        return newShopItem;
    }

    public void toProfileActivity() {
        Button profileScreenButton = (Button)findViewById(R.id.to_profile_button_shop);
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
        Button historyScreenButton = (Button)findViewById(R.id.to_history_button_shop);
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
        Button timerScreenButton = (Button)findViewById(R.id.to_timer_button_shop);
        final Intent timerSetIntent = TimerSetActivity.buildIntent(this);
        timerScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(timerSetIntent);
                finish();
            }
        });
    }

    public void toSettingsActivity() {
        Button settingsScreenButton = (Button)findViewById(R.id.to_settings_button_shop);
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
