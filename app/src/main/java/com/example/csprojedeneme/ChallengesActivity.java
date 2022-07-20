package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChallengesActivity extends AppCompatActivity {

    private Button createBtn;
    private Button meetBtn;
    private Button prevChallangeFirst;
    private Button prevChallangeSecond;
    private Button prevChallangeThird;

    public static int selectedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        createBtn = (Button) findViewById(R.id.createBtn);
        meetBtn = (Button) findViewById(R.id.meetBtn);
        prevChallangeFirst = (Button) findViewById(R.id.prevChallangeFirst);
        prevChallangeSecond = (Button) findViewById(R.id.prevChallangeSecond);
        prevChallangeThird = (Button) findViewById(R.id.prevChallangeThird);

    }
    public void createBtnClick(View v){
        Intent intent = new Intent(this, CreateChallenge.class);
        startActivity(intent);
    }
    public void meetBtnClick(View v){
        Intent intent = new Intent(this, MeetChallenge.class);
        startActivity(intent);
    }
    public void prevChallengeFirstClick(View v){
        selectedButton = 1;
        Intent intent = new Intent(this, ChallengeScreenActivity.class);
        startActivity(intent);
    }
    public void prevChallengeSecondClick(View v){
        selectedButton = 2;
        Intent intent = new Intent(this, ChallengeScreenActivity.class);
        startActivity(intent);
    }
    public void prevChallengeThirdClick(View v){
        selectedButton = 3;
        Intent intent = new Intent(this, ChallengeScreenActivity.class);
        startActivity(intent);
    }
}