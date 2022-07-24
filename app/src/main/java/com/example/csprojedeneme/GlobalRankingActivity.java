package com.example.csprojedeneme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GlobalRankingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GlobalRankingAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<User > usersGet;
    private ArrayList<RankingUserItem> mListToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_ranking);

        loadUsers();

    }
    public void loadUsers(){
        usersGet = new ArrayList<>();
        mListToAdd = new ArrayList<>();
        MainActivity.usersRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    User user = documentSnapshot.toObject(User.class);
                    usersGet.add(documentSnapshot.toObject(User.class));
                }
                sortUsers();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortUsers(){
        usersGet.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getXp() < o2.getXp()){return 1;}
                else if(o1.getXp() > o2.getXp()){return -1;}
                return 0;
            }
        });
        createList();
    }
    public void createList(){
        for(int i = 0; i < usersGet.size(); i++){
            User user = usersGet.get(i);
            mListToAdd.add(new RankingUserItem(user.getPlansDone(), user.getChallengesWon(),
                    user.getUsername(), user.getXp()));
        }
        buildRecyclerView();
    }
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new GlobalRankingAdapter(mListToAdd);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}