package com.example.dcgamescollection.StatsRecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dcgamescollection.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcgamescollection.Pojo.Stats;

import java.util.ArrayList;

public class CustomStatsRecycler extends RecyclerView.Adapter<CustomStatsRecycler.CustomViewHolder> {

    private ArrayList<Stats> statsList;
    private Context context;

    public CustomStatsRecycler(ArrayList<Stats> statsList, Context context) {
        this.statsList = statsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_stats_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Stats stats = statsList.get(position);
        holder.title.setText(stats.getName());
        holder.highScore.setText("High Score: " + stats.getHigh_score());
        holder.kills.setText("Kills: " + stats.getKills());
        holder.deaths.setText("Deaths: " + stats.getDeaths());
        holder.assists.setText("Assists: " + stats.getAssists());
        holder.wins.setText("Wins: " + stats.getWins());
        holder.losses.setText("Losses: " + stats.getLost());
    }

    @Override
    public int getItemCount() {
        if(statsList != null) {
            return statsList.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView title;
        protected TextView highScore;
        protected TextView kills;
        protected TextView deaths;
        protected TextView assists;
        protected TextView wins;
        protected TextView losses;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.statsTitle);
            this.highScore = itemView.findViewById(R.id.statsHighScore);
            this.kills = itemView.findViewById(R.id.statsKills);
            this.deaths = itemView.findViewById(R.id.statsDeaths);
            this.assists = itemView.findViewById(R.id.statsAssists);
            this.wins = itemView.findViewById(R.id.statsWins);
            this.losses = itemView.findViewById(R.id.statsLosses);
        }
    }

}
