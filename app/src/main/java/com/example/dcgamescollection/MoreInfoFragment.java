package com.example.dcgamescollection;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dcgamescollection.ImageViewPager.ImageViewAdapter;
import com.example.dcgamescollection.Pojo.Games;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Games games;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String GAMES = "name";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoreInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreInfoFragment newInstance(String param1, String param2) {
        MoreInfoFragment fragment = new MoreInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_info, container, false);
        ConstraintLayout layout = view.findViewById(R.id.infoLayout);
        ViewPager2 imageViewPager = view.findViewById(R.id.gameImage);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        TextView nameTextView = view.findViewById(R.id.gameNameLabel);
        TextView rateTextView = view.findViewById(R.id.rateTV);
        TextView dateTextView = view.findViewById(R.id.dateTV);
        TextView ageTextView = view.findViewById(R.id.ageRateTV);
        TextView genres = view.findViewById(R.id.genreTV);
        TextView platforms = view.findViewById(R.id.platformTV);
        TextView tags = view.findViewById(R.id.tagsTV);
        TextView playTime = view.findViewById(R.id.platTimeTV);
        TextView store = view.findViewById(R.id.storeTV);
        if(getArguments() != null) {
            games = getArguments().getParcelable(GAMES);
            if (games != null) {
                nameTextView.setText(games.getName());
                rateTextView.setText(String.valueOf(games.getRating()));
                dateTextView.setText(games.getReleaseDate());
                ageTextView.setText(games.getEsrbRating());
                String genre = String.join(", ", games.getGenres());
                genres.setText(genre);
                platforms.setText(String.join(", ", games.getPlatforms()));
                tags.setText(String.join(", ", games.getTags()));
                if (games.getPlaytime() != 0) {
                    playTime.setText(games.getPlaytime() + " Hour");
                } else {
                    playTime.setText("Not Available");
                }
                store.setText(String.join(", ", games.getStores()));
                imageViewPager.setAdapter(new ImageViewAdapter(getActivity(), games.getScreenShots()));
                imageViewPager.setPageTransformer(new ZoomOutPageTransformer());

            }
            new TabLayoutMediator(tabLayout, imageViewPager,
                    (tab, position) -> tab.setText("")
            ).attach();
        }


        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.info_animation);
        layout.startAnimation(animation);
        return view;
    }

    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}