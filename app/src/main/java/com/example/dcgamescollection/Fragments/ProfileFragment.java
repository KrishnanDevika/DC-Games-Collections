package com.example.dcgamescollection.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dcgamescollection.HomeFragment;
import com.example.dcgamescollection.R;
import com.example.dcgamescollection.Settings.CustomSettingsListAdapter;
import com.example.dcgamescollection.Settings.SettingsOptions;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        ListView detailsList = view.findViewById(R.id.detailsList);

        ArrayList<SettingsOptions> settingOptions = new ArrayList<>();

        String name = HomeFragment.getName;
        String dob = HomeFragment.getDob;


        if(name != null || dob != null){
            settingOptions.add(new SettingsOptions(R.drawable.ic_baseline_person_24, "Name",name ));
            settingOptions.add(new SettingsOptions(R.drawable.ic_baseline_cake_24, "Date of Birth", dob));

        }else{
            settingOptions.add(new SettingsOptions(R.drawable.ic_baseline_person_24, "Name","" ));
            settingOptions.add(new SettingsOptions(R.drawable.ic_baseline_cake_24, "Date of Birth", ""));
            Toast.makeText(getContext(), "Please fill the User Profile in the Home Page", Toast.LENGTH_SHORT).show();

        }

        detailsList.setAdapter(new CustomSettingsListAdapter(getContext(), settingOptions));

        return view;
    }
}