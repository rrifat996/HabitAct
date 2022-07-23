package com.example.csprojedeneme;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class FriendListActivity extends AppCompatActivity {
    private ArrayList<FriendListUserItem> mExampleList;

    private RecyclerView mRecyclerView;
    private FriendsItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int count = 0;
    private ArrayList<String > friendsId;

    private String lastRemovedId;
    private ArrayList<String> lastRemovedsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        createExampleList();
    }


    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeItem(int position, String text) {
        mExampleList.get(position).setUsername(text);
        mAdapter.notifyItemChanged(position);
    }


    public void createExampleList() {
        mExampleList = new ArrayList<>();
        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                friendsId = documentSnapshot.toObject(User.class).getFriendList();

                createExampleList2();
            }
        });
    }
    public void createExampleList2() {
        for(int i = 0; i < friendsId.size(); i++){
            addDocumentIdToList(friendsId.get(i));
        }


    }
    public void addDocumentIdToList(String id){
        MainActivity.usersRef.document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                mExampleList.add(new FriendListUserItem(user.getPlansDone(), user.getChallengesWon(),
                        user.getUsername(), user.getXp()));
                count++;
                if(count == friendsId.size()){
                    buildRecyclerView();
                }

            }
        });
    }
    public void removeFriend(int position){
        lastRemovedId = friendsId.get(position);
        friendsId.remove(position);
        ArrayList<String> newFriendsList = friendsId;
        MainActivity.userRef.update("friendList", newFriendsList).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(FriendListActivity.this,"friend removed",
                        Toast.LENGTH_SHORT).show();
                removeFromOther(lastRemovedId);

            }
        });
    }
    public void removeFromOther(String lastRemovedId){
        MainActivity.usersRef.document(lastRemovedId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //      String docId = documentSnapshot.getId();
                lastRemovedsList = documentSnapshot.toObject(User.class).getFriendList();
                finalRemove(lastRemovedId, SaveSharedPreference.getUserId(FriendListActivity.this));
            }
        });
    }
    public void finalRemove(String idToRemoveFrom, String lastRemovedId){
        lastRemovedsList.remove(SaveSharedPreference.getUserId(FriendListActivity.this));
        ArrayList<String> newList = lastRemovedsList;
        MainActivity.usersRef.document(idToRemoveFrom).update("friendList", newList)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(FriendListActivity.this,"friend removed 2 successful",
                                Toast.LENGTH_SHORT).show();
                    }
                });


    }
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new FriendsItemAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FriendsItemAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {
                removeFriend(position);
                removeItem(position);
            }
        });
    }

}