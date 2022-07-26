package com.example.csprojedeneme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    private Button storeButton,weeklyPlanButton,challengesButton,statisticsButton,settingsButton;
    private TextView welcomeText;
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private ImageView topItem, botItem, dressItem, hatItem, shoesItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_acitivity);

        storeButton = (Button)findViewById(R.id.storeButton);
        weeklyPlanButton = (Button)findViewById(R.id.weeklyPlanButton);
        challengesButton = (Button)findViewById(R.id.challengesButton);
        statisticsButton = (Button)findViewById(R.id.statisticsButton);
        settingsButton = (Button)findViewById(R.id.settingsButton);
        topItem = (ImageView)findViewById(R.id.topItem);
        botItem = (ImageView)findViewById(R.id.botItem);
        dressItem = (ImageView)findViewById(R.id.dressItem);
        hatItem = (ImageView)findViewById(R.id.hatItem);
        shoesItem = (ImageView)findViewById(R.id.shoesItem);


        if(Event.trigger == 0){
            MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String str = "Welcome Back " + documentSnapshot.toObject(User.class).getRealName();
                    Toast.makeText(HomePageActivity.this, str,
                            Toast.LENGTH_SHORT).show();
                }
            });
            Event.trigger++;
        }
        wearItems();

    }

    public void wearItems(){
        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                itemArrayList = documentSnapshot.toObject(User.class).getItems();
                for (Item item : itemArrayList) {
                    int count = 0;
                    System.out.println(item.getCategory());

                    if (item.getCategory().equalsIgnoreCase("Top")){
                        Glide.with(getBaseContext())
                                .load(item.getId())
                                .into(topItem);
                    }
                    else if (item.getCategory().equalsIgnoreCase("Bottom")){
                        Glide.with(getBaseContext())
                                .load(item.getId())
                                .into(botItem);
                    }
                    else if (item.getCategory().equalsIgnoreCase("Dress")){
                        Glide.with(getBaseContext())
                                .load(item.getId())
                                .into(dressItem);
                    }
                    else if (item.getCategory().equalsIgnoreCase("Hat")){
                        Glide.with(getBaseContext())
                                .load(item.getId())
                                .into(hatItem);
                    }
                    else if (item.getCategory().equalsIgnoreCase("Shoes")){
                        Glide.with(getBaseContext())
                                .load(item.getId())
                                .into(shoesItem);
                    }
                }
            }
        });

    }
    public void storeMenu(View v){
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void weeklyPlanMenu(View v){
        Intent intent = new Intent(this, WeekViewActivity.class);
        startActivity(intent);
    }
    public void challengesMenu(View v){
        Intent intent = new Intent(this, ChallengesActivity.class);
        startActivity(intent);
    }
    public void statisticsMenu(View v){
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
    public void settingsMenu(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void onBackPressed() {
        this.finishAffinity();
    }

}