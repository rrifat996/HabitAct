package com.example.csprojedeneme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GlobalRankingAdapter extends RecyclerView.Adapter<GlobalRankingAdapter.ExampleViewHolder> {
    private ArrayList<UserListingItem> mUsers;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView plansDone;
        public TextView challengesWon;
        public TextView username;
        public TextView xp;

        public ExampleViewHolder(View itemView){
            super(itemView);

            username = itemView.findViewById(R.id.globalUsername2);
            challengesWon = itemView.findViewById(R.id.globalChalllengesCompleted2);
            xp = itemView.findViewById(R.id.globalXp2);

        }

    }
    public GlobalRankingAdapter(ArrayList<UserListingItem> users){
        mUsers = users;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_user_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(GlobalRankingAdapter.ExampleViewHolder holder, int position) {
        UserListingItem currentItem = mUsers.get(position);

        holder.xp.setText("" +currentItem.getXp());
        holder.username.setText(currentItem.getUsername());
        holder.challengesWon.setText("" +currentItem.getChallengesWon());



    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
