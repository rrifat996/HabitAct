package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public class CreatingCharacterActivity extends AppCompatActivity {
    private EditText enterName;
    private Button continueButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creating_character);

        enterName = (EditText)findViewById(R.id.enterName);
        continueButton = (Button)findViewById(R.id.continueButton);

    }
    public void continueTo(View v){
        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Toast.makeText(CreatingCharacterActivity.this,"Welcome to HabitAct",
                        Toast.LENGTH_SHORT).show();
                MainActivity.userRef.update("realName", enterName.getText().toString());
            }
        });
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
}