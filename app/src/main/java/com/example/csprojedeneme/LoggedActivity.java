package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoggedActivity extends AppCompatActivity {
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
        MainActivity.loggedUser.setRealName(enterName.getText().toString());
        Intent intent = new Intent(this, HomePageAcitivity.class);
        startActivity(intent);
    }
}