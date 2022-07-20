package com.example.csprojedeneme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter {
    private ArrayList<Challenge> mChallenges;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
            mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public TextView mDescription;
        public TextView mCreatorName;
       // private String creatorId;


        public ExampleViewHolder(View itemView, OnItemClickListener listener){
            super(itemView);

            mTitle = itemView.findViewById(R.id.mTitle);
            mDescription = itemView.findViewById(R.id.mDescription);
            mCreatorName = itemView.findViewById(R.id.mCreatorName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int positon = getAbsoluteAdapterPosition();
                        if(positon != RecyclerView.NO_POSITION){
                            listener.onItemClick(positon);
                        }
                    }
                }
            });
        }

    }
    public ChallengeAdapter(ArrayList<Challenge> challenges){
        mChallenges = challenges;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
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
