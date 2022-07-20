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
    private ArrayList<RankingUserItem> mUsers;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView plansDone;
        public TextView challengesWon;
        public TextView username;
        public TextView xp;

        public ExampleViewHolder(View itemView){
            super(itemView);

            plansDone = itemView.findViewById(R.id.globalPlansCompleted2);
            username = itemView.findViewById(R.id.globalUsername2);
            challengesWon = itemView.findViewById(R.id.globalChalllengesCompleted2);
            xp = itemView.findViewById(R.id.globalXp2);

        }

    }
    public GlobalRankingAdapter(ArrayList<RankingUserItem> users){
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
        RankingUserItem currentItem = mUsers.get(position);


        holder.plansDone.setText("" +currentItem.getPlansDone());
        holder.challengesWon.setText("" +currentItem.getChallengesWon());
        holder.username.setText(currentItem.getUsername());
        holder.xp.setText("" +currentItem.getXp());

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
