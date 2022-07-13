package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class HomePageAcitivity extends AppCompatActivity {

    private Button settingsButton;
    private TextView welcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_acitivity);

        settingsButton = (Button)findViewById(R.id.settingsButton);
        welcomeText = (TextView) findViewById(R.id.welcomeText);

        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(HomePageAcitivity.this,"got name",
                        Toast.LENGTH_SHORT).show();

                welcomeText.setText("Welcome, " + documentSnapshot.toObject(User.class).getRealName());
            }
        });

    }
    public void settingsMenu(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}