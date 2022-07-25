package com.example.csprojedeneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class StoreActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView item1, item2, item3, item4, item5, item6;
    private Item storeItem1, storeItem2, storeItem3, storeItem4, storeItem5, storeItem6;
    private Item[] list = {storeItem1, storeItem2, storeItem3, storeItem4, storeItem5, storeItem6};

    private int userGold;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        item1 = (ImageView)findViewById(R.id.marketItem1);
        item2 = (ImageView)findViewById(R.id.marketItem2);
        item3 = (ImageView)findViewById(R.id.marketItem3);
        item4 = (ImageView)findViewById(R.id.marketItem4);
        item5 = (ImageView)findViewById(R.id.marketItem5);
        item6 = (ImageView)findViewById(R.id.marketItem6);

        item1.setOnClickListener((View.OnClickListener) this);
        item2.setOnClickListener((View.OnClickListener) this);
        item3.setOnClickListener((View.OnClickListener) this);
        item4.setOnClickListener((View.OnClickListener) this);
        item5.setOnClickListener((View.OnClickListener) this);
        item6.setOnClickListener((View.OnClickListener) this);
        placeItems();

    }
    public void getUserGold(){
        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userGold = documentSnapshot.toObject(User.class).getGold();
            }
        });
    }

    @Override
    public void onClick(View view) {

        MainActivity.userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                switch (view.getId()){
                    case R.id.marketItem1:
                        buy(user, list[0]);
                        break;
                    case R.id.marketItem2:
                        buy(user, list[1]);
                        break;
                    case R.id.marketItem3:
                        buy(user, list[2]);
                        break;
                    case R.id.marketItem4:
                        buy(user, list[3]);
                        break;
                    case R.id.marketItem5:
                        buy(user, list[4]);
                        break;
                    case R.id.marketItem6:
                        buy(user, list[5]);
                        break;
                }
            }
        });
    }

    public void placeItems(){
        ArrayList<Item> randomItems = new ArrayList<>();
        ArrayList<ImageView> imageViews = new ArrayList<>();
        imageViews.add(item1);
        imageViews.add(item2);
        imageViews.add(item3);
        imageViews.add(item4);
        imageViews.add(item5);
        imageViews.add(item6);

        MainActivity.itemsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    if(count == 6){
                        break;
                    }
                    Item item = documentSnapshot.toObject(Item.class);
                    list[count] = new Item(item.getId(), item.getCost(), item.getCategory());
                    randomItems.add(item);
                    drawItem(randomItems.get(count).getId(), imageViews.get(count));
                    count++;
                }
                for (int i = 0; i < randomItems.size(); i++){
                    drawItem(randomItems.get(i).getId(), imageViews.get(i));
                }
            }
        });

    }

    public void drawItem(String id, ImageView imageView) {

        Glide.with(getBaseContext())
                .load(id)
                .into(imageView);
    }

    public void dayPassed(){

        Calendar calendar;
        SimpleDateFormat simpleDateFormat;
        String date;

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm::ss");
        refreshItems();

    }

    public void refreshItems(){

    }

    public void buy(User user, Item item){
        if (user.getGold() >= item.getCost()){
            user.setGold(user.getGold() - item.getCost());
            user.addCollection(item);
            MainActivity.userRef.update("gold", user.getGold() - item.getCost()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(StoreActivity.this,"purchased",
                            Toast.LENGTH_SHORT).show();
                }
            });
            MainActivity.itemRef.update("category", item.getCategory()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(StoreActivity.this,"Purchased",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(StoreActivity.this, "Not Enough Gold", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
}