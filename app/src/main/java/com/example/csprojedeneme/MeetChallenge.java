package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MeetChallenge extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ChallengeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText searchText;

    private Button searchBtn;
    private Button meetBtn2;
    private Button addCreatorFriendBtn;

    private ArrayList<Challenge> challenges;


    private String[] splitted;

    private String[] toUpdate;

    private String lastSelectedId;
    private String lastSelectedCreatorId;

    private DocumentReference creatorRef;

    private int clickedIdx;

    private ArrayList<String> tempFriends;
    private ArrayList<String> tempFriends2;

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

        String searchTextStr = searchText.getText().toString();
        splitted = searchTextStr.split(" ");
        MainActivity.challengesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Challenge ch = documentSnapshot.toObject(Challenge.class);
                    if(ch.isMeeted()){
                        addIfMoreThanHalfTitle(documentSnapshot.toObject(Challenge.class));
                    }
                }
                recyclerBuilder();
            }

        });

    }
    public void recyclerBuilder(){
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MeetChallenge.this);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ChallengeAdapter(challenges);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ChallengeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                clickedIdx = position;
                lastSelectedId = challenges.get(position).getId();
                lastSelectedCreatorId = challenges.get(position).getCreatorId();
                creatorRef = MainActivity.usersRef.document(lastSelectedCreatorId);
                // addChallenge(challenges.get(position).getId());
            }
        });
    }
    public void updateUser(String first, String second, String third){
        MainActivity.userRef.update("challange1", first).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });
        MainActivity.userRef.update("challange2", second).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });
        MainActivity.userRef.update("challange3", third).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        });
    }

    public void updateUserOther(String first, String second, String third){
        creatorRef.update("challange1", first).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MeetChallenge.this,"Challenges updated",
                        Toast.LENGTH_SHORT).show();
            }
        });
        MainActivity.userRef.update("challange2", second).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });
        MainActivity.userRef.update("challange3", third).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });
    }
    public void addChallenge(String id){

        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                User user = documentSnapshot.toObject(User.class);

                if(!id.equals(user.getChallange1()) && !id.equals(user.getChallange2()) && !id.equals(user.getChallange3())){
                    String first = user.getChallange1();
                    String second = user.getChallange2();
                    String third;

                    third = second;
                    second = first;
                    first = id;

                    updateUser(first, second, third);
                }
                else{
                    Toast.makeText(MeetChallenge.this,"You already met with this challenge!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void addChallengeToOther(String id){

        creatorRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);

                if(!id.equals(user.getChallange1()) && !id.equals(user.getChallange2()) && !id.equals(user.getChallange3())){
                    String first = user.getChallange1();
                    String second = user.getChallange2();
                    String third;

                    third = second;
                    second = first;
                    first = id;

                    updateUserOther(first, second, third);
                }
                else{
                    Toast.makeText(MeetChallenge.this,"You already met with this challenge",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void addIfMoreThanHalfTitle(Challenge challenge){
        String[] secondSplit = challenge.getChallengeName().split(" ");
        int count = 0;
        for(String searchItem : splitted){
            count = 0;
            for(String challengeItem : secondSplit){
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
        if(lastSelectedCreatorId.equals("")){
            Toast.makeText(MeetChallenge.this,"Select a challenge first!",
                    Toast.LENGTH_SHORT).show();
        }
        else if(lastSelectedCreatorId.equals(SaveSharedPreference.getUserId(MeetChallenge.this))){Toast.makeText(MeetChallenge.this,"You cannot meet with your own challenge!",
                Toast.LENGTH_SHORT).show();
        }
        else{
            MainActivity.challengesRef.document(lastSelectedId).update("meeted", false).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    addChallenge(lastSelectedId);
                    addChallengeToOther(lastSelectedId);
                }
            });

        }
    }
    public void addCreatorFriendBtnClick(View v){
        if(lastSelectedCreatorId.equals(SaveSharedPreference.getUserId(MeetChallenge.this))){
            Toast.makeText(MeetChallenge.this,"You cannot be friends with yourself!",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    tempFriends = documentSnapshot.toObject(User.class).getFriendList();
                    boolean isFound = false;
                    for(String otherId : tempFriends){
                        if(otherId.equals(lastSelectedCreatorId)){
                            Toast.makeText(MeetChallenge.this,"You are already friends with this user!",
                                    Toast.LENGTH_SHORT).show();

                            isFound = true;
                            break;
                        }
                    }
                    if(!isFound){
                        addFriend();
                        addCreatorFriendBtnClick2(v);
                    }
                }
            });
        }
    }
    public void addCreatorFriendBtnClick2(View v){
        creatorRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                tempFriends2 = documentSnapshot.toObject(User.class).getFriendList();
                addFriendOther();
            }
        });
    }
    public void addFriend(){
        tempFriends.add(lastSelectedCreatorId);
        MainActivity.userRef.update("friendList", tempFriends);
    }
    public void addFriendOther(){
        tempFriends2.add(SaveSharedPreference.getUserId(MeetChallenge.this));
        creatorRef.update("friendList", tempFriends2);
    }
}
