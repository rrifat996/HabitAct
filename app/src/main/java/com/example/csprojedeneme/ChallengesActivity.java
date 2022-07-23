package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class ChallengesActivity extends AppCompatActivity {

    private Button createBtn;
    private Button meetBtn;
    private Button prevChallangeFirst;
    private Button prevChallangeSecond;
    private Button prevChallangeThird;

    private TextView prevText1;
    private TextView prevText2;
    private TextView prevText3;

    private ImageView prev1;
    private ImageView prev2;
    private ImageView prev3;

    private boolean isActiveFirst;
    private boolean isActiveSecond;
    private boolean isActiveThird;

    private int resoure1;
    private int resoure2;
    private int resoure3;

    private String ch1;
    private String ch2;
    private String ch3;


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

        prev1 = (ImageView) findViewById(R.id.prev1);
        prev2 = (ImageView) findViewById(R.id.prev2);
        prev3 = (ImageView) findViewById(R.id.prev3);

        prevText1 = (TextView) findViewById(R.id.prevText1);
        prevText2 = (TextView) findViewById(R.id.prevText2);
        prevText3 = (TextView) findViewById(R.id.prevText3);

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateScreen();
    }

    public void updateScreen(){
        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                if(user.getChallange1().equals("")){
                    isActiveFirst = false;
                }
                else{
                    isActiveFirst = true;
                    updateVariables1(user.getChallange1());
                }
                if(user.getChallange2().equals("")){
                    isActiveSecond = false;
                }
                else{
                    isActiveSecond = true;
                    updateVariables2(user.getChallange2());
                }
                if(user.getChallange3().equals("")){
                    isActiveThird = false;
                }
                else{
                    isActiveThird = true;
                    updateVariables3(user.getChallange3());
                }


            }
        });
    }
    public void updateVariables1(String chId){
        MainActivity.challengesRef.document(chId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Challenge ch = documentSnapshot.toObject(Challenge.class);
                ch1 = ch.getChallengeName();
                resoure1 = ch.getmImageResource();
                prevText1.setText(ch1);
                setImages();
            }
        });
    }
    public void updateVariables2(String chId){
        MainActivity.challengesRef.document(chId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Challenge ch = documentSnapshot.toObject(Challenge.class);
                ch2 = ch.getChallengeName();
                resoure2 = ch.getmImageResource();
                prevText2.setText(ch2);
                setImages();
            }
        });
    }
    public void updateVariables3(String chId){
        MainActivity.challengesRef.document(chId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Challenge ch = documentSnapshot.toObject(Challenge.class);
                ch3 = ch.getChallengeName();
                resoure3 = ch.getmImageResource();
                prevText3.setText(ch3);
                setImages();
            }
        });
    }
    public void setImages(){
        if(isActiveFirst){
            prev1.setImageResource(resoure1);
        }
        if(isActiveSecond){
            prev2.setImageResource(resoure2);
        }
        if(isActiveThird){
            prev3.setImageResource(resoure3);
        }

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
        if(!isActiveFirst){
            Toast.makeText(ChallengesActivity.this,"you do not have challenge here",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            selectedButton = 1;
            Intent intent = new Intent(this, ChallengeScreenActivity.class);
            startActivity(intent);
        }
    }
    public void prevChallengeSecondClick(View v){
        if(!isActiveSecond){
            Toast.makeText(ChallengesActivity.this,"you do not have challenge here",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            selectedButton = 2;
            Intent intent = new Intent(this, ChallengeScreenActivity.class);
            startActivity(intent);
        }
    }
    public void prevChallengeThirdClick(View v){
        if(!isActiveThird){
            Toast.makeText(ChallengesActivity.this,"you do not have challenge here",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            selectedButton = 3;
            Intent intent = new Intent(this, ChallengeScreenActivity.class);
            startActivity(intent);
        }
    }

}