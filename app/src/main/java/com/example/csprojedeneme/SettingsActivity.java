package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        logoutButton = (Button)findViewById(R.id.logoutButton);
    }
    public void logout(View v){
        SharedPreferences preferences = getSharedPreferences("CheckBox", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember", "false");
        editor.apply();
        finish();
        SaveSharedPreference.clearUserName(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}