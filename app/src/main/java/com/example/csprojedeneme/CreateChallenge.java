package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView imageImgResource;

    private ImageView firstImageView;
    private ImageView secondImageView;
    private ImageView thirdImageView;
    private ImageView fourthImageView;
    private ImageView fifthImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createa_challange);

        createBtn2 = (Button) findViewById(R.id.createBtn2);
        challengeName = (EditText)findViewById(R.id.challengeName);
        description = (EditText)findViewById(R.id.description);

        firstImageView = (ImageView) findViewById(R.id.first);
        secondImageView = (ImageView) findViewById(R.id.second);
        thirdImageView = (ImageView) findViewById(R.id.third);
        fourthImageView = (ImageView) findViewById(R.id.fourth);
        fifthImageView = (ImageView) findViewById(R.id.fifth);

        imageResource = R.drawable.ic_baseline_accessibility_24;

        imageImgResource = firstImageView;
        firstImageView.setBackgroundResource(R.drawable.image_view_border);

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
        imageImgResource.setBackgroundResource(R.drawable.image_view_border_zero);
        imageImgResource = firstImageView;
        imageResource = R.drawable.ic_baseline_accessibility_24;
        firstImageView.setBackgroundResource(R.drawable.image_view_border);
    }
    public void secondImgClick(View v){
        imageImgResource.setBackgroundResource(R.drawable.image_view_border_zero);
        imageImgResource = secondImageView;
        imageResource = R.drawable.ic_baseline_date_range_24;
        secondImageView.setBackgroundResource(R.drawable.image_view_border);
    }
    public void thirdImgClick(View v){
        imageImgResource.setBackgroundResource(R.drawable.image_view_border_zero);
        imageImgResource = thirdImageView;
        imageResource = R.drawable.ic_baseline_food_bank_24;
        thirdImageView.setBackgroundResource(R.drawable.image_view_border);
    }
    public void fourthImgClick(View v){
        imageImgResource.setBackgroundResource(R.drawable.image_view_border_zero);
        imageImgResource = fourthImageView;
        imageResource = R.drawable.ic_baseline_sports_football_24;
        fourthImageView.setBackgroundResource(R.drawable.image_view_border);
    }
    public void fifthImgClick(View v){
        imageImgResource.setBackgroundResource(R.drawable.image_view_border_zero);
        imageImgResource = fifthImageView;
        imageResource = R.drawable.ic_baseline_timer_24;
        fifthImageView.setBackgroundResource(R.drawable.image_view_border);
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
