package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }
    public void friendsRankingClick(View v){
        Intent intent = new Intent(this, FriendsRankingActivity.class);
        startActivity(intent);
    }
    public void globalRankingClick(View v){
        Intent intent = new Intent(this, GlobalRankingActivity.class);
        startActivity(intent);
    }

}