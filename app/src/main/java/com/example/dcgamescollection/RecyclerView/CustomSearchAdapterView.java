package com.example.dcgamescollection.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

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
    }

    @Override
    public int getItemCount() {
        if(gamesList != null){
            Log.d("GameSearch", String.valueOf(gamesList.size()));
            return  gamesList.size();
        }
        return 0;
    }

    class GameViewHolder extends RecyclerView.ViewHolder {

        protected TextView gameName;
        protected TextView gameRating;
        protected TextView gameReleaseDate;
        //protected ViewPager2 gameImage;
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
