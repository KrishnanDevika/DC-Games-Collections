package com.example.dcgamescollection.ImageViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import java.util.ArrayList;

public class ImageViewAdapter extends FragmentStateAdapter {
    ArrayList<String > images;
    public ImageViewAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<String> images) {
        super(fragmentActivity);
        this.images = images;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ImageFragment.newInstance(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
