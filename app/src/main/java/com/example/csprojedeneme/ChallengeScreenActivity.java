package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ChallengeScreenActivity extends AppCompatActivity {
    private Button progressButton;
    private TextView challengeTitle;
    private TextView challengeDescription;

    private ProgressBar ownProgressBar;
    private ProgressBar opponentProgressBar;
    private String currentChallengeId;
    private String creatorId;
    private boolean isUserCreator;
    private DocumentSnapshot currentChallenge;
    private DocumentReference currentChallengeRef;
    private int prevCountFromDatabase;
    private int prevXpFromDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_screen);

        ownProgressBar = findViewById(R.id.ownProgressBar);
        opponentProgressBar = findViewById(R.id.opponentProgressBar);

        progressButton = (Button) findViewById(R.id.progressButton);
        challengeTitle = (TextView) findViewById(R.id.challengeTitle);
        challengeDescription = (TextView) findViewById(R.id.challengeDescription);

        setDocumentId();

    }
    public void setDocumentId(){
        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                if(ChallengesActivity.selectedButton == 1){
                    currentChallengeId = user.getChallange1();
                }
                else if(ChallengesActivity.selectedButton == 2){
                    currentChallengeId = user.getChallange2();
                }
                else if(ChallengesActivity.selectedButton == 3){
                    currentChallengeId = user.getChallange3();
                }
                currentChallengeRef = MainActivity.challengesRef.document(currentChallengeId);
                findChallenge();
            }
        });

    }
    public void findChallenge(){
        MainActivity.challengesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    if(currentChallengeId.equals(documentSnapshot.getId())){
                        creatorId = documentSnapshot.toObject(Challenge.class).getCreatorId();
                        currentChallenge = documentSnapshot;
                        challengeTitle.setText(documentSnapshot.toObject(Challenge.class).getChallengeName());
                        challengeTitle.setText(documentSnapshot.toObject(Challenge.class).getDescription());
                        break;
                    }
                }
                isUserCreator();
            }
        });
    }
    public void isUserCreator(){
        isUserCreator = SaveSharedPreference.getUserId(ChallengeScreenActivity.this).equals(creatorId);
        setProgresses();
    }
    public void setProgresses(){
        if(isUserCreator){
            ownProgressBar.setProgress(currentChallenge.toObject(Challenge.class).getCreatorProgress());
            opponentProgressBar.setProgress(currentChallenge.toObject(Challenge.class).getMeeterProgress());
        }
        else{
            ownProgressBar.setProgress(currentChallenge.toObject(Challenge.class).getMeeterProgress());
            opponentProgressBar.setProgress(currentChallenge.toObject(Challenge.class).getCreatorProgress());
        }
    }
    public void addProgressBtn(View v){
        if(isUserCreator){
            currentChallengeRef.update("creatorProgress", ownProgressBar.getProgress() + 10);
        }
        else{
            currentChallengeRef.update("meeterProgress", ownProgressBar.getProgress() + 10);
        }
        ownProgressBar.incrementProgressBy(10);
        checkIfWon();
    }
    public void checkIfWon(){
        if(ownProgressBar.getProgress() == 100 && currentChallenge.toObject(Challenge.class).isActive()){
            progressButton.setVisibility (View.GONE);
            currentChallengeRef.update("isActive", false);
            Toast.makeText(ChallengeScreenActivity.this,"YOU WON",
                    Toast.LENGTH_SHORT).show();
            getUserChallengesWon();
        }
    }
    public void getUserChallengesWon(){
        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                prevCountFromDatabase = user.getChallengesWon();
                prevXpFromDatabase = user.getXp();
                updateUser();
            }
        });
    }


    public void updateUser(){
        MainActivity.userRef.update("challengesWon", prevCountFromDatabase + 1);
        MainActivity.userRef.update("xp", prevXpFromDatabase + 10);

    }
}