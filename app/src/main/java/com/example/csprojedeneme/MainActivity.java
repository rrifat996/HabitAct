package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    private EditText ediTextUsername2;
    private EditText editTextPassword2;
    private EditText editTextPassword3;

    private Button btnLogin;
    private Button btnSignup;
    private Button btnSignup2;
    

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        editTextUsername = findViewById(R.id.editTextUsername2);
        editTextPassword = findViewById(R.id.editTextPassword2);
        editTextPassword = findViewById(R.id.editTextPassword3);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup2 = findViewById(R.id.btnSignup2);

        btnLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

    }
    public void createUser(){

    }
    public void loginUser(){

    }
}