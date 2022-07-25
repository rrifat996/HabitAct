package com.example.csprojedeneme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FriendsItemAdapter extends RecyclerView.Adapter<FriendsItemAdapter.ExampleViewHolder> {
    private ArrayList<UserListingItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView challengesWon;
        public TextView username;
        public TextView xp;
        public ImageView mDeleteImage;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            username = itemView.findViewById(R.id.globalUsername);
            challengesWon = itemView.findViewById(R.id.challengesWon);
            xp = itemView.findViewById(R.id.globalXp);
            mDeleteImage = itemView.findViewById(R.id.image_delete);


            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public FriendsItemAdapter(ArrayList<UserListingItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        UserListingItem currentItem = mExampleList.get(position);
        holder.challengesWon.setText("" +currentItem.getChallengesWon());
        holder.username.setText(currentItem.getUsername());
        holder.xp.setText("" +currentItem.getXp());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}