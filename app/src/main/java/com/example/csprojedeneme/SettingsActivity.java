package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logoutButton = (Button)findViewById(R.id.logoutButton);
    }
    public void logout(View v){
        SaveSharedPreference.setUserId(this, "");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}