package com.example.csprojedeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
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

    private boolean userFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SaveSharedPreference.getUserName(MainActivity.this).length() == 0)
        {
            //Maybe change later
        }
        else
        {
            usersRef.get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
            Intent intent = new Intent(this, HomePageAcitivity.class);
            startActivity(intent);

        }

        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button)findViewById(R.id.btnSignup);


    }
    public void signup(){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
    public void signupClick(View v){
        Toast.makeText(MainActivity.this,"signup clicked",
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
                            Toast.makeText(MainActivity.this,"buldum accountu",
                                    Toast.LENGTH_SHORT).show();
                            SaveSharedPreference.setUserName(getApplicationContext(), usernameForShared);
                            logged();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"account bulunamadı",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

        });

    }

}