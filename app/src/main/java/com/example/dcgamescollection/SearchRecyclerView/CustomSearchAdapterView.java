package com.example.dcgamescollection.SearchRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcgamescollection.GamesCollectionDatabase;
import com.example.dcgamescollection.MoreInfoFragment;
import com.example.dcgamescollection.Pojo.Games;
import com.example.dcgamescollection.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Author : Devika Krishnan
 * Date : Mar 29, 2022
 * Class : CustomSearchAdapterView
 */
public class CustomSearchAdapterView extends RecyclerView.Adapter<CustomSearchAdapterView.GameViewHolder> {

    private ArrayList<Games> gamesList;
    private Context context;
    private Bundle extra;

    public CustomSearchAdapterView(ArrayList<Games> gamesList, Context context, Bundle extra) {
        this.gamesList = gamesList;
        this.context = context;
        this.extra = extra;
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
        holder.gameRating.setText(String.valueOf(games.getRating()));
        holder.gameReleaseDate.setText(games.getReleaseDate());
        if(extra.getString("action_type").equals("add")) {
            holder.save.setText("Mark");
            holder.gameRating.setVisibility(View.GONE);
            holder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    {
                        try {
                            date = sdf.parse(holder.gameReleaseDate.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, "Release Date: " + holder.gameName)
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date);
                    if(intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                        Toast.makeText(context, "Date added to Calendar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if(extra.getString("action_type").equals("save")) {
            holder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GamesCollectionDatabase db = new GamesCollectionDatabase(context);
                    boolean isPresent = false;
                    for(Games addedGame : db.getAllGames()){
                        if(addedGame.getName().equals(games.getName())){
                            isPresent = true;
                            holder.save.setEnabled(false);
                        }
                    }
                    if(isPresent){
                        Toast.makeText(context, "Game Already Added", Toast.LENGTH_SHORT).show();
                    }else {
                        db.addGames(games);
                        Toast.makeText(context, "Game Added", Toast.LENGTH_SHORT).show();
                        holder.save.setText("Added");
                        holder.save.setEnabled(false);
                    }
                }
            });
        }
        Picasso.with(context).load(games.getGameIcon()).into(holder.gameImage);
        holder.webImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = games.getName();
                name  = name.replace(" ", "-");
                String url = "https://rawg.io/games/"+name;
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                context.startActivity(intent);
            }
        });

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extra = new Bundle();
                extra.putParcelable(MoreInfoFragment.GAMES,
                        gamesList.get(holder.getAdapterPosition()));
                Navigation.findNavController(view).navigate(R.id.moreInfoFragment, extra);

            }
        });

        holder.mExp.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        if(gamesList != null){
            return  gamesList.size();
        }
        return 0;
    }

    public void clear() {
        gamesList.clear();
    }

    class GameViewHolder extends RecyclerView.ViewHolder {

        protected TextView gameName;
        protected TextView gameRating;
        protected TextView gameReleaseDate;
        protected ImageView gameImage;
        protected Button save;
        protected Button more;
        protected Button mExp;
        protected ImageView webImage;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            this.gameName = itemView.findViewById(R.id.gameLabel);
            this.gameRating = itemView.findViewById(R.id.ratingLabel);
            this.gameReleaseDate = itemView.findViewById(R.id.dateLabel);
            this.more = itemView.findViewById(R.id.infoButton);
            this.save = itemView.findViewById(R.id.saveButton);
            this.gameImage = itemView.findViewById(R.id.imageView);
            this.mExp = itemView.findViewById(R.id.mExpButton);
            this.webImage = itemView.findViewById(R.id.webView);
        }
    }
}
