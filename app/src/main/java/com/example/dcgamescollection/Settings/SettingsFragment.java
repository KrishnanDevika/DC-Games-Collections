package com.example.dcgamescollection.Settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dcgamescollection.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        ListView settingsList = view.findViewById(R.id.settingsList);
        LinearLayout layout = view.findViewById(R.id.settingLayout);

        ArrayList<SettingsOptions> settingOptions = new ArrayList<>();
        settingOptions.add(new SettingsOptions( R.drawable.ic_baseline_person_24,"Personal Details", "Name, DOB"));
        settingOptions.add(new SettingsOptions(R.drawable.ic_baseline_share_24, "Share App", ""));
        settingOptions.add(new SettingsOptions(R.drawable.ic_baseline_remember_me_24, "Credits" , "Thanks to"));

        settingsList.setAdapter(new CustomSettingsListAdapter(getContext(), settingOptions));
        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        Navigation.findNavController(view).navigate(R.id.nav_profile);
                        break;
                    case 1:
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT,"Help you to manage and track all your games Collection in one place");
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                    case 2:
                        Navigation.findNavController(view).navigate(R.id.nav_credits);
                        break;
                }
            }
        });
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_up);
        layout.startAnimation(animation);
        return  view;
    }
}