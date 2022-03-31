package com.example.dcgamescollection.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dcgamescollection.R;

public class UpcomingGamesAdapter extends RecyclerView.Adapter<UpcomingGamesAdapter.CustomHolder>{

    private Context context;

    @NonNull
    @Override
    public UpcomingGamesAdapter.CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_game_item, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingGamesAdapter.CustomHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        protected ViewPager2 imageViewPager;
        protected TextView gameName;
        protected TextView releaseDate;
        protected TextView rating;
        protected Button moreButton;
        protected Button saveButton;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            this.imageViewPager = itemView.findViewById(R.id.viewPager2);
            this.gameName = itemView.findViewById(R.id.textView2);
            this.releaseDate = itemView.findViewById(R.id.textView5);
            this.rating = itemView.findViewById(R.id.textView4);
            this.moreButton = itemView.findViewById(R.id.button2);
            this.saveButton = itemView.findViewById(R.id.button3);
        }
    }

}
