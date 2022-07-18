package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MeetChallenge extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText searchText;

    private Button searchBtn;
    private Button meetBtn2;
    private Button addCreatorFriendBtn;

    private ArrayList<Challenge> challenges;
    private String[] splitted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_challenge);

        searchText = (EditText)findViewById(R.id.searchText);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        meetBtn2 = (Button) findViewById(R.id.meetBtn2);
        addCreatorFriendBtn = (Button) findViewById(R.id.addCreatorFriendBtn);

        challenges = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);

    }
    public void searchBtnClick(View v){
        challenges.clear();
        Toast.makeText(MeetChallenge.this,"before ref",
                Toast.LENGTH_SHORT).show();
        String searchTextStr = searchText.getText().toString();
        splitted = searchTextStr.split(" ");
        MainActivity.challengesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    Challenge challenge = documentSnapshot.toObject(Challenge.class);
                    Toast.makeText(MeetChallenge.this,"ref achieved",
                            Toast.LENGTH_SHORT).show();

                    addIfMoreThanHalfTitle(challenge);

                }
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(MeetChallenge.this);


                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new ExampleAdapter(challenges);
                mRecyclerView.setAdapter(mAdapter);
            }

        });

    }
    public void addIfMoreThanHalfTitle(Challenge challenge){
        String[] secondSplit = challenge.getChallengeName().split(" ");
        int count = 0;
        for(String searchItem : secondSplit){
            count = 0;
            for(String challengeItem : splitted){
                if(searchItem.equalsIgnoreCase(challengeItem)){
                    count++;
                }
            }
            if(count >= splitted.length / 2 + 1) {
                challenges.add(challenge);
            }
        }

    }
    public void meetBtn2Click(View v){

    }
    public void addCreatorFriendBtnClick(View v){

    }
}