package com.example.dcgamescollection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AskUserRecord#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AskUserRecord extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AskUserRecord() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AskUserRecord.
     */
    // TODO: Rename and change types and number of parameters
    public static AskUserRecord newInstance(String param1, String param2) {
        AskUserRecord fragment = new AskUserRecord();
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
        View view = inflater.inflate(R.layout.fragment_ask_user_record, container, false);

        ToggleButton changeView = view.findViewById(R.id.toggleButton);

//All the TextField in the form to take input of user
        TextView HighScoreTitle = view.findViewById(R.id.highscoreTitle);
        TextView KillsTitle = view.findViewById(R.id.killsTitle);
        TextView DeathsTitle = view.findViewById(R.id.deathsTitle);
        TextView AssistsTitle = view.findViewById(R.id.assistsTitle);
        TextView WinsTitle = view.findViewById(R.id.winsTitle);
        TextView LostTitle = view.findViewById(R.id.lostTitle);

//All the input box in the form to take input of user
        EditText InputhighScore = view.findViewById(R.id.socreInputField);
        EditText InputKills = view.findViewById(R.id.killsInputField);
        EditText InputDeaths = view.findViewById(R.id.deathInputField);
        EditText InputAssists = view.findViewById(R.id.assistsInputField);
        EditText InputWins = view.findViewById(R.id.winInputField);
        EditText InputLost = view.findViewById(R.id.lostInputField);


        changeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(changeView.isChecked()){
                    HighScoreTitle.setVisibility(View.INVISIBLE);
                    HighScoreTitle.setHeight(0);
                    InputhighScore.setVisibility(View.INVISIBLE);
                    InputhighScore.setHeight(0);

                    KillsTitle.setVisibility(View.VISIBLE);
                    KillsTitle.setHeight(50);
                    InputKills.setVisibility(View.VISIBLE);
                    InputKills.setHeight(50);

                    DeathsTitle.setVisibility(View.VISIBLE);
                    DeathsTitle.setHeight(50);
                    InputDeaths.setVisibility(View.VISIBLE);
                    InputDeaths.setHeight(50);

                    AssistsTitle.setVisibility(View.VISIBLE);
                    AssistsTitle.setHeight(50);
                    InputAssists.setVisibility(View.VISIBLE);
                    InputAssists.setHeight(50);

                    WinsTitle.setVisibility(View.VISIBLE);
                    WinsTitle.setHeight(50);
                    InputWins.setVisibility(View.VISIBLE);
                    InputWins.setHeight(50);

                    LostTitle.setVisibility(View.VISIBLE);
                    LostTitle.setHeight(50);
                    InputLost.setVisibility(View.VISIBLE);
                    InputLost.setHeight(50);


                }else{
                    HighScoreTitle.setVisibility(View.VISIBLE);
                    HighScoreTitle.setHeight(50);
                    InputhighScore.setVisibility(View.VISIBLE);
                    InputhighScore.setHeight(50);

                    KillsTitle.setVisibility(View.INVISIBLE);
                    KillsTitle.setHeight(0);
                    InputKills.setVisibility(View.INVISIBLE);
                    InputKills.setHeight(0);

                    DeathsTitle.setVisibility(View.INVISIBLE);
                    DeathsTitle.setHeight(0);
                    InputDeaths.setVisibility(View.INVISIBLE);
                    InputDeaths.setHeight(0);

                    AssistsTitle.setVisibility(View.INVISIBLE);
                    AssistsTitle.setHeight(0);
                    InputAssists.setVisibility(View.INVISIBLE);
                    InputAssists.setHeight(0);

                    WinsTitle.setVisibility(View.INVISIBLE);
                    WinsTitle.setHeight(0);
                    InputWins.setVisibility(View.INVISIBLE);
                    InputWins.setHeight(0);

                    LostTitle.setVisibility(View.INVISIBLE);
                    LostTitle.setHeight(0);
                    InputLost.setVisibility(View.INVISIBLE);
                    InputLost.setHeight(0);

                }
            }
        });

        return view;
    }
}