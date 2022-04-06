package com.example.dcgamescollection.SearchRecyclerView;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcgamescollection.MoreInfoFragment;
import com.example.dcgamescollection.Pojo.Games;
import com.example.dcgamescollection.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Author : Devika Krishnan
 * Date : Mar 29, 2022
 * Class : CustomSearchAdapterView
 */
public class CustomSearchAdapterView extends RecyclerView.Adapter<CustomSearchAdapterView.GameViewHolder> {

    private ArrayList<Games> gamesList;
    private Context context;

    public CustomSearchAdapterView(ArrayList<Games> gamesList, Context context) {
        this.gamesList = gamesList;
        this.context = context;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.search_game_item, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Games games = gamesList.get(position);
        holder.gameName.setText(games.getName());
        holder.gameRating.setText("Rating: "+String.valueOf(games.getRating()));
        holder.gameReleaseDate.setText(games.getReleaseDate());
        Picasso.with(context).load(games.getGameIcon()).into(holder.gameImage);

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extra = new Bundle();
                extra.putParcelable(MoreInfoFragment.GAMES,
                        gamesList.get(holder.getAdapterPosition()));
                Navigation.findNavController(view).navigate(R.id.moreInfoFragment, extra);

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

    class GameViewHolder extends RecyclerView.ViewHolder {

        protected TextView gameName;
        protected TextView gameRating;
        protected TextView gameReleaseDate;
        protected ImageView gameImage;
        protected Button save;
        protected Button more;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            this.gameName = itemView.findViewById(R.id.gameLabel);
            this.gameRating = itemView.findViewById(R.id.ratingLabel);
            this.gameReleaseDate = itemView.findViewById(R.id.dateLabel);
            this.more = itemView.findViewById(R.id.infoButton);
            this.save = itemView.findViewById(R.id.saveButton);
            this.gameImage = itemView.findViewById(R.id.imageView);
        }
    }
}
