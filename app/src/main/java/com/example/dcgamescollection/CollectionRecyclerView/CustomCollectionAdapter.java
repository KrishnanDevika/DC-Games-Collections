package com.example.dcgamescollection.CollectionRecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcgamescollection.Pojo.Games;
import com.example.dcgamescollection.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author chintan
 * @Date: March 30th 2022
 *
 * @Content: Creating CustomCollectionAdapter
 */
public class CustomCollectionAdapter extends RecyclerView.Adapter<CustomCollectionAdapter.CustomViewHolder> {

    private ArrayList<Games> gamesList;
    private Context context;

    public CustomCollectionAdapter(ArrayList<Games> gamesList, Context context) {
        this.gamesList = gamesList;
        this.context = context;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_game_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Games games = gamesList.get(position);
        holder.gameName.setText(games.getName());
        holder.gameRating.setText("Rating: "+String.valueOf(games.getRating()));
        holder.gameReleaseDate.setText(games.getReleaseDate());
        Picasso.with(context).load(games.getGameIcon()).into(holder.gameImage);
        holder.save.setVisibility(View.INVISIBLE);
        holder.more.setVisibility(View.INVISIBLE);
        holder.mExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        if(gamesList != null){
            return  gamesList.size();
        }
        return 0;
    }

    /**
     * @author chintan
     * @date march 30th 2022
     *
     * @content
     * * Creating View Holder For CustomCollectionAdapter.
     *
     */
    class CustomViewHolder extends RecyclerView.ViewHolder{

        protected TextView gameName;
        protected TextView gameRating;
        protected TextView gameReleaseDate;
        protected ImageView gameImage;
        protected Button save;
        protected Button more;
        protected Button mExp;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.gameName = itemView.findViewById(R.id.gameLabel);
            this.gameRating = itemView.findViewById(R.id.ratingLabel);
            this.gameReleaseDate = itemView.findViewById(R.id.dateLabel);
            this.more = itemView.findViewById(R.id.infoButton);
            this.save = itemView.findViewById(R.id.saveButton);
            this.gameImage = itemView.findViewById(R.id.imageView);
            this.mExp = itemView.findViewById(R.id.mExpButton);
        }
    }
}
