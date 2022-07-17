package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button btnLogin;
    private Button btnSignup;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");

    public static User loggedUser;
    private CheckBox remember;

    private boolean userFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        remember = findViewById(R.id.rememberMe);

        if(SaveSharedPreference.getUserName(MainActivity.this).length() == 0)
        {
            //Maybe change later
        }
        else
        {
            usersRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                User user = documentSnapshot.toObject(User.class);
                                if(user.getUsername().equals(SaveSharedPreference.getUserName(MainActivity.this))){
                                    loggedUser = user;
                                    break;
                                }
                            }

                        }
                    });
        }

        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button)findViewById(R.id.btnSignup);

        SharedPreferences preferences = getSharedPreferences("CheckBox", MODE_PRIVATE);
        String CheckBox = preferences.getString("remember","");

        if(CheckBox.equals("true")){
            Intent intent = new Intent(MainActivity.this,HomePageAcitivity.class);
            startActivity(intent);
        }
        else if(CheckBox.equals("false")){
            Toast.makeText(this,"Please sign in.", Toast.LENGTH_SHORT).show();
        }

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("CheckBox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(MainActivity.this,"Checked",Toast.LENGTH_SHORT).show();
                }
                else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("CheckBox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(MainActivity.this,"Unchecked",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void signup(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    public void signupClick(View v){
        Toast.makeText(MainActivity.this,"Sign up clicked",
                Toast.LENGTH_SHORT).show();
        signup();
    }
    public void logged(){

        Intent intent = new Intent(this, HomePageAcitivity.class);
        startActivity(intent);
    }

    public void lgnClick(View v){
        userFound = false;
        usersRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String usernameForShared = "";
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            User user = documentSnapshot.toObject(User.class);
                            String username = user.getUsername();
                            usernameForShared = username;
                            String password = user.getPassword();
                            if(editTextUsername.getText().toString(). equals(username)){
                                if(editTextPassword.getText().toString().equals(password)){
                                    userFound = true;
                                    loggedUser = user;
                                    break;
                                }
                            }
                        }
                        if(userFound){
                            Toast.makeText(MainActivity.this,"Account Found, Login successful.",
                                    Toast.LENGTH_SHORT).show();
                            SaveSharedPreference.setUserName(getApplicationContext(), usernameForShared);
                            logged();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Account not found.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

        });

    }

}