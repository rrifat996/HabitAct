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
        Intent intent = new Intent(this, CreateChallange.class);
        startActivity(intent);
    }
    public void meetBtnClick(View v){
        Intent intent = new Intent(this, MeetChallenge.class);
        startActivity(intent);
    }
    public void prevChallangeFirstClick(View v){
        Intent intent = new Intent(this, FirstChallenge.class);
        startActivity(intent);
    }
    public void prevChallangeSecondClick(View v){
        Intent intent = new Intent(this, SecondChallenge.class);
        startActivity(intent);
    }
    public void prevChallangeThirdClick(View v){
        Intent intent = new Intent(this, ThirdChallenge.class);
        startActivity(intent);
    }
}