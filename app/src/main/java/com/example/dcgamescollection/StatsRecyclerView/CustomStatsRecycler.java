package com.example.dcgamescollection.StatsRecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dcgamescollection.AskUserRecord;
import com.example.dcgamescollection.R;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
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
        //Game is a High-Score
        if (stats.getGameType()) {
            holder.highScore.setVisibility(View.VISIBLE);
            //holder.highScore.setHeight(80);
            holder.highScore.setText("High Score: " + stats.getHigh_score());
            holder.kills.setVisibility(View.GONE);
            //holder.kills.setHeight(0);
            holder.deaths.setVisibility(View.GONE);
            //holder.deaths.setHeight(0);
            holder.assists.setVisibility(View.GONE);
            //holder.assists.setHeight(0);
            holder.wins.setVisibility(View.GONE);
            //holder.wins.setHeight(0);
            holder.losses.setVisibility(View.GONE);
            //holder.losses.setHeight(0);
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle extra = new Bundle();
                    extra.putParcelable(AskUserRecord.STATS, stats);
                    extra.putInt(AskUserRecord.ACTION_TYPE, AskUserRecord.UPDATE);
                    Navigation.findNavController(view).navigate(R.id.ask_user_record, extra);
                }
            });
        }
        //Game is a ScoreBoard
        else {
            holder.highScore.setVisibility(View.GONE);
            //holder.highScore.setHeight(0);
            holder.kills.setVisibility(View.VISIBLE);
            //holder.kills.setHeight(80);
            holder.kills.setText("Kills: " + stats.getKills());
            holder.deaths.setVisibility(View.VISIBLE);
            //holder.deaths.setHeight(80);
            holder.deaths.setText("Deaths: " + stats.getDeaths());
            holder.assists.setVisibility(View.VISIBLE);
            //holder.assists.setHeight(80);
            holder.assists.setText("Assists: " + stats.getAssists());
            holder.wins.setVisibility(View.VISIBLE);
            //holder.wins.setHeight(80);
            holder.wins.setText("Wins: " + stats.getWins());
            holder.losses.setVisibility(View.VISIBLE);
            //holder.losses.setHeight(80);
            holder.losses.setText("Losses: " + stats.getLost());
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle extra = new Bundle();
                    extra.putParcelable(AskUserRecord.STATS, stats);
                    extra.putInt(AskUserRecord.ACTION_TYPE, AskUserRecord.UPDATE);
                    Navigation.findNavController(view).navigate(R.id.ask_user_record, extra);
                }
            });
        }

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
        protected Button edit;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.statsTitle);
            this.highScore = itemView.findViewById(R.id.statsHighScore);
            this.kills = itemView.findViewById(R.id.statsKills);
            this.deaths = itemView.findViewById(R.id.statsDeaths);
            this.assists = itemView.findViewById(R.id.statsAssists);
            this.wins = itemView.findViewById(R.id.statsWins);
            this.losses = itemView.findViewById(R.id.statsLosses);
            this.edit = itemView.findViewById(R.id.editButton);
        }
    }

}
