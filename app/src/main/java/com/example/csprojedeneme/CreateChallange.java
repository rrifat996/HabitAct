package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class CreateChallange extends AppCompatActivity {

    private EditText challengeName;
    private EditText description;
    private Button createBtn2;
    private String docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createa_challange);

        createBtn2 = (Button) findViewById(R.id.createBtn2);
        challengeName = (EditText)findViewById(R.id.challengeName);
        description = (EditText)findViewById(R.id.description);
    }
    public void createBtnClick2(View v) {
        String title = challengeName.getText().toString();
        String desc = description.getText().toString();

        Challenge challenge = new Challenge(title, SaveSharedPreference.getUserId(CreateChallange.this), desc);
        MainActivity.challengesRef.add(challenge).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {  // maybe get id later (meet)
                Toast.makeText(CreateChallange.this,"document added database",
                        Toast.LENGTH_SHORT).show();
            }
        });




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
