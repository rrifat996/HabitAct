package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePageAcitivity extends AppCompatActivity {

    private Button storeButton,weeklyPlanButton,challengesButton,statisticsButton,settingsButton;
    //private TextView welcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        storeButton = (Button)findViewById(R.id.storeButton);
        weeklyPlanButton = (Button)findViewById(R.id.weeklyPlanButton);
        challengesButton = (Button)findViewById(R.id.challengesButton);
        statisticsButton = (Button)findViewById(R.id.statisticsButton);
        settingsButton = (Button)findViewById(R.id.settingsButton);

    }
    public void storeMenu(View v){
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);
    }
    public void weeklyPlanMenu(View v){
        Intent intent = new Intent(this, WeeklyPlanActivity.class);
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
}