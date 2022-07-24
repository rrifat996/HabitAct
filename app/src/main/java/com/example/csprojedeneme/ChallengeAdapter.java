package com.example.csprojedeneme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter {
    private ArrayList<Challenge> mChallenges;
    private int singleItemSelection = -1;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public TextView mDescription;
        public TextView mCreatorName;
        public ImageView mIcon;
        public ConstraintLayout changeColor;


        public ExampleViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.mTitle);
            mDescription = itemView.findViewById(R.id.mDescription);
            mCreatorName = itemView.findViewById(R.id.mCreatorName);
            mIcon = itemView.findViewById(R.id.mImage);
            changeColor= itemView.findViewById(R.id.changeColor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int positon = getAbsoluteAdapterPosition();
                        if(positon != RecyclerView.NO_POSITION){
                            listener.onItemClick(positon);
                            setSingleSelection(positon);

                        }
                    }
                }
            });
        }
    }
    private void setSingleSelection(int adapterPosition){
        if(adapterPosition == RecyclerView.NO_POSITION){
            return;
        }
        notifyItemChanged(singleItemSelection);
        singleItemSelection = adapterPosition;
        notifyItemChanged(singleItemSelection);
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
        if(singleItemSelection == position){
            holder.itemView.findViewById(R.id.changeColor).setBackgroundResource(R.drawable.image_view_border);
        }else{
            holder.itemView.findViewById(R.id.changeColor).setBackgroundResource(R.drawable.image_view_border_zero);
        }

        Challenge currentChallenge = mChallenges.get(position);

        ExampleViewHolder holder1 = (ExampleViewHolder)holder;

        holder1.mCreatorName.setText(currentChallenge.getCreatorName());
        holder1.mDescription.setText(currentChallenge.getDescription());
        holder1.mTitle.setText(currentChallenge.getChallengeName());
        holder1.mIcon.setImageResource(currentChallenge.getmImageResource());
        //holder1.changeColor.setBackgroundResource(R.drawable.image_view_border);

    }

    @Override
    public int getItemCount() {
        return mChallenges.size();
    }
}
