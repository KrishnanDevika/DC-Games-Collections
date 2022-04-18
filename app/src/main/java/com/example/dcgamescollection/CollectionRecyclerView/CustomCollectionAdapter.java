package com.example.dcgamescollection.CollectionRecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dcgamescollection.GamesCollectionDatabase;
import com.example.dcgamescollection.AskUserRecord;
import com.example.dcgamescollection.MoreInfoFragment;
import com.example.dcgamescollection.Pojo.Games;
import com.example.dcgamescollection.R;
import com.google.android.material.navigation.NavigationView;
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
        holder.gameRating.setText(String.valueOf(games.getRating()));
        holder.gameReleaseDate.setText(games.getReleaseDate());
        Picasso.with(context).load(games.getGameIcon()).into(holder.gameImage);
        holder.save.setVisibility(View.INVISIBLE);
        holder.more.setVisibility(View.INVISIBLE);
        holder.mExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extra = new Bundle();
                extra.putParcelable(AskUserRecord.STATS,
                        gamesList.get(holder.getAdapterPosition()));
                Navigation.findNavController(view).navigate(R.id.ask_user_record, extra);
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
    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

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
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Are you sure you want to delete " +
                            gamesList.get(getLayoutPosition()).getName() + "?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            GamesCollectionDatabase db = new GamesCollectionDatabase(context);
                            //Delete record from database
                            db.deleteGame(gamesList.get(getLayoutPosition()).getId());
                            //Delete the record from the ArrayList
                            gamesList.remove(getLayoutPosition());
                            //Notify the RecyclerView the item was removed
                            notifyItemRemoved(getAdapterPosition());
                            db.close();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return false;

        }
    }
}
