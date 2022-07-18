package com.example.csprojedeneme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter {
    private ArrayList<Challenge> mChallenges;


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public TextView mDescription;
        public TextView mCreatorName;
       // private String creatorId;


        public ExampleViewHolder(View itemView){
            super(itemView);

            mTitle = itemView.findViewById(R.id.mTitle);
            mDescription = itemView.findViewById(R.id.mDescription);
            mCreatorName = itemView.findViewById(R.id.mCreatorName);

        }

    }
    public ExampleAdapter(ArrayList<Challenge> challenges){
        mChallenges = challenges;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Challenge currentChallenge = mChallenges.get(position);

        ExampleViewHolder holder1 = (ExampleViewHolder)holder;
        holder1.mCreatorName.setText(currentChallenge.getCreatorName());
        holder1.mDescription.setText(currentChallenge.getDescription());
        holder1.mTitle.setText(currentChallenge.getChallengeName());

    }

    @Override
    public int getItemCount() {
        return mChallenges.size();
    }
}
