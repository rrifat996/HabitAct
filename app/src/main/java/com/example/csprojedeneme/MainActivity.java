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
import com.google.firebase.firestore.DocumentReference;
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

    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static CollectionReference challengesRef = db.collection("challenges");
    public static CollectionReference usersRef = db.collection("users");
    public static DocumentReference userRef;

    private boolean userFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button)findViewById(R.id.btnSignup);

        //This mean it has already login before (did not logout afterwards) directed to homepage
        if(SaveSharedPreference.getUserId(MainActivity.this).length() != 0)
        {
            userRef = usersRef.document(SaveSharedPreference.getUserId(MainActivity.this));
            Intent intent = new Intent(this, HomePageAcitivity.class);
            startActivity(intent);
        }

    }
    public void logged(){
        Intent intent = new Intent(this, HomePageAcitivity.class);
        startActivity(intent);
    }

    public void signupClick(View v){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public void lgnClick(View v){
        userFound = false;
        usersRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            User user = documentSnapshot.toObject(User.class);
                            String username = user.getUsername();
                            String password = user.getPassword();
                            if(editTextUsername.getText().toString(). equals(username)){
                                if(editTextPassword.getText().toString().equals(password)){
                                    userFound = true;
                                    SaveSharedPreference.setUserId(MainActivity.this, documentSnapshot.getId());
                                    userRef = usersRef.document(SaveSharedPreference.getUserId(MainActivity.this));
                                    break;
                                }
                            }
                        }
                        if(userFound){
                            Toast.makeText(MainActivity.this,"buldum accountu",
                                    Toast.LENGTH_SHORT).show();
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