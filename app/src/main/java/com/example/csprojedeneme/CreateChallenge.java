package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class CreateChallenge extends AppCompatActivity {

    private EditText challengeName;
    private EditText description;
    private Button createBtn2;
    private String docId;
    private DocumentReference docRef;
    private String userName;
    private int imageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createa_challange);

        createBtn2 = (Button) findViewById(R.id.createBtn2);
        challengeName = (EditText)findViewById(R.id.challengeName);
        description = (EditText)findViewById(R.id.description);
    }
    public void getBack(){
        Intent intent = new Intent(this, ChallengesActivity.class);
        startActivity(intent);
    }
    public void createBtnClick2(View v) {
        String title = challengeName.getText().toString();
        String desc = description.getText().toString();

        Challenge challenge = new Challenge(title, SaveSharedPreference.getUserId(CreateChallenge.this), desc);
        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(CreateChallenge.this,"got name",
                        Toast.LENGTH_SHORT).show();
                userName = documentSnapshot.toObject(User.class).getRealName();
                //            welcomeText.setText("Welcome, " + documentSnapshot.toObject(User.class).getRealName());
            }
        });
        challenge.setCreatorName(userName);
        challenge.setmImageResource(imageResource);
        MainActivity.challengesRef.add(challenge).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {  // maybe get id later (meet)
                Toast.makeText(CreateChallenge.this,"document added database",
                        Toast.LENGTH_SHORT).show();
                docId = documentReference.getId();
                docRef = documentReference;
                setId();
                setCreator();
            }
        });
        getBack();
    }
    public void setId(){
        docRef.update("id", docId);
    }
    public void setCreator(){
        docRef.update("creatorName", userName);
    }
    public void firstImgClick(View v){
        imageResource = R.drawable.ic_baseline_accessibility_24;
    }
    public void secondImgClick(View v){
        imageResource = R.drawable.ic_baseline_date_range_24;
    }
    public void thirdImgClick(View v){
        imageResource = R.drawable.ic_baseline_food_bank_24;
    }
    public void fourthImgClick(View v){
        imageResource = R.drawable.ic_baseline_sports_football_24;
    }
    public void fifthImgClick(View v){
        imageResource = R.drawable.ic_baseline_timer_24;
    }

}
/*
* User user = MainActivity.userRef.toObject(User.class);
                        String[] temp = user.getLastChallengesIds();
                        temp[1] = temp[0];
                        temp[2] = temp[1];
                        temp[0] = documentSnapshot1.getId();*/
/*
* MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(HomePageAcitivity.this,"got name",
                        Toast.LENGTH_SHORT).show();

                welcomeText.setText("Welcome, " + documentSnapshot.toObject(User.class).getRealName());
            }
        });*/
/*
*                           MainActivity.usersRef.get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                                    User user = documentSnapshot.toObject(User.class);
                                                    String username = user.getUsername();
                                                    if(user.getUsername().equals(typedUsername)){
                                                        SaveSharedPreference.setUserId(MainActivity3.this, documentSnapshot.getId());
                                                        MainActivity.userRef = MainActivity.usersRef.document(SaveSharedPreference.getUserId(MainActivity3.this));
                                                        break;
                                                    }
                                                }
                                            }
                                         });
*
* */
