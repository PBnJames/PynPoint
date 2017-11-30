package com.example.android.pynpoint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (mPrefs.getBoolean("purple", false)) {
            setTheme(R.style.ActivityTheme_Primary_Base_Purple);
        } else if(mPrefs.getBoolean("green", false)){
            setTheme(R.style.ActivityTheme_Primary_Base_Green);
        } else if(mPrefs.getBoolean("red", false)){
            setTheme(R.style.ActivityTheme_Primary_Base_Red);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mListView = (ListView) findViewById(R.id.shop_list_view);
        String row2Lock = "Locked";
        String row3Lock = "Locked";
        String row5Lock = "Locked";

        if(mPrefs.getInt("points", 0)>= 3){
            row2Lock = "Unlocked";
            row3Lock = "Unlocked";
            row5Lock = "Unlocked";

        } else if(mPrefs.getInt("points", 0) >= 2){
            row2Lock = "Unlocked";
            row3Lock = "Unlocked";

        }else if(mPrefs.getInt("points", 0) >= 1){
            row2Lock = "Unlocked";
        }

        ShopItem row1 = createShopItem("Purple", "0 Points", "Unlocked", getResources().getDrawable(R.drawable.purplebox));
        ShopItem row2 = createShopItem("Green", "1 Points", row2Lock, getResources().getDrawable(R.drawable.greenbox));
        ShopItem row3 = createShopItem("Red", "2 Points", row3Lock, getResources().getDrawable(R.drawable.redbox));
        ShopItem row4 = createShopItem("Dog Icon", "0 Points", "Unlocked",getResources().getDrawable(R.drawable.doghead));
        ShopItem row5 = createShopItem("Cat Icon", "3 Points", row5Lock,getResources().getDrawable(R.drawable.cathead));

        final ArrayList<ShopItem> shopList = new ArrayList<ShopItem>();
        shopList.add(row1);
        shopList.add(row2);
        shopList.add(row3);
        shopList.add(row4);
        shopList.add(row5);


        ShopAdapter adapter = new ShopAdapter(this, shopList);
        mListView.setAdapter(adapter);


        onListViewItemClick();


        toTimerSetActivity();
        toSettingsActivity();
        toHistoryActivity();
        toProfileActivity();
    }

    public void onListViewItemClick() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences mPrefs =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = mPrefs.edit();
                
                if(position == 0){
                    editor.putBoolean("purple", true);
                    editor.putBoolean("green", false);
                    editor.putBoolean("red", false);
                }else if(position == 1){
                    if(mPrefs.getInt("points", 0) >= 1) {
                        editor.putBoolean("purple", false);
                        editor.putBoolean("green", true);
                        editor.putBoolean("red", false);
                    }else{
                        Toast.makeText(ShopActivity.this, "Item Locked: Not enough points", Toast.LENGTH_LONG).show();
                    }
                }else if(position == 2){
                    if(mPrefs.getInt("points", 0) >= 2) {
                        editor.putBoolean("purple", false);
                        editor.putBoolean("green", false);
                        editor.putBoolean("red", true);
                    }else{
                        Toast.makeText(ShopActivity.this, "Item Locked: Not enough points", Toast.LENGTH_LONG).show();
                    }
                }else if(position == 3){
                    editor.putBoolean("dog",true);
                    editor.putBoolean("cat", false);
                }else if(position == 4){
                    if(mPrefs.getInt("points", 0) >= 3) {
                        editor.putBoolean("dog",false);
                        editor.putBoolean("cat", true);
                    }else{
                        Toast.makeText(ShopActivity.this, "Item Locked: Not enough points", Toast.LENGTH_LONG).show();
                    }
                }

                editor.apply();
            }
        });
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
