package com.example.dcgamescollection.CollectionRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcgamescollection.R;

/**
 * @author chintan
 * @Date: March 30th 2022
 *
 * @Content: Creating CustomCollectionAdapter
 */
public class CustomCollectionAdapter extends RecyclerView.Adapter<CustomCollectionAdapter.CustomViewHolder> {

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_game_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
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

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
