package com.example.dcgamescollection;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.dcgamescollection.Database.StatsDatabase;
import com.example.dcgamescollection.Pojo.Games;
import com.example.dcgamescollection.Pojo.Stats;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

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
    public static final String STATS = "stats";


    Games games;

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
        Stats stats = new Stats();

        ToggleButton changeView = view.findViewById(R.id.toggleButton);

        TextView NameTitle = view.findViewById(R.id.StatsGameTitle);
        games = getArguments().getParcelable(STATS);
        NameTitle.setText(games.getName());

//All the TextField in the form to take input of user
        TextView HighScoreTitle = view.findViewById(R.id.highscoreTitle);
        TextView KillsTitle = view.findViewById(R.id.killsTitle);
        TextView DeathsTitle = view.findViewById(R.id.deathsTitle);
        TextView AssistsTitle = view.findViewById(R.id.assistsTitle);
        TextView WinsTitle = view.findViewById(R.id.winsTitle);
        TextView LostTitle = view.findViewById(R.id.lostTitle);

//All the input box in the form to take input of user
        EditText InputHighScore = view.findViewById(R.id.socreInputField);
        EditText InputKills = view.findViewById(R.id.killsInputField);
        EditText InputDeaths = view.findViewById(R.id.deathInputField);
        EditText InputAssists = view.findViewById(R.id.assistsInputField);
        EditText InputWins = view.findViewById(R.id.winInputField);
        EditText InputLost = view.findViewById(R.id.lostInputField);

        Button SubmitButton = view.findViewById(R.id.submitButton);
        String gameName = "";
        if(getArguments() != null) {
            games = getArguments().getParcelable(STATS);
            if (games != null) {
                gameName = games.getName();
            }
        }
        String finalGameName = gameName;
        Log.d("ASK", finalGameName);




        changeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (changeView.isChecked()) {
                    HighScoreTitle.setVisibility(View.INVISIBLE);
                    HighScoreTitle.setHeight(0);
                    InputHighScore.setVisibility(View.INVISIBLE);
                    InputHighScore.setHeight(0);

                    KillsTitle.setVisibility(View.VISIBLE);
                    KillsTitle.setHeight(80);
                    InputKills.setVisibility(View.VISIBLE);
                    InputKills.setHeight(80);

                    DeathsTitle.setVisibility(View.VISIBLE);
                    DeathsTitle.setHeight(80);
                    InputDeaths.setVisibility(View.VISIBLE);
                    InputDeaths.setHeight(80);

                    AssistsTitle.setVisibility(View.VISIBLE);
                    AssistsTitle.setHeight(80);
                    InputAssists.setVisibility(View.VISIBLE);
                    InputAssists.setHeight(80);

                    WinsTitle.setVisibility(View.VISIBLE);
                    WinsTitle.setHeight(80);
                    InputWins.setVisibility(View.VISIBLE);
                    InputWins.setHeight(80);

                    LostTitle.setVisibility(View.VISIBLE);
                    LostTitle.setHeight(80);
                    InputLost.setVisibility(View.VISIBLE);
                    InputLost.setHeight(80);

                    SubmitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            stats.setName(finalGameName);
                            //stats.setHigh_score(Integer.parseInt(InputHighScore.getText().toString()));
                            stats.setKills(Integer.parseInt(InputKills.getText().toString()));
                            stats.setDeaths(Integer.parseInt(InputDeaths.getText().toString()));
                            stats.setAssists(Integer.parseInt(InputAssists.getText().toString()));
                            stats.setWins(Integer.parseInt(InputWins.getText().toString()));
                            stats.setLost(Integer.parseInt(InputLost.getText().toString()));
                            stats.setGameType(false);

                            StatsDatabase db = new StatsDatabase(getContext());
                            db.addStats(stats);
                            db.close();
                            Navigation.findNavController(view).popBackStack();
                        }
                    });

                } else {
                    HighScoreTitle.setVisibility(View.VISIBLE);
                    HighScoreTitle.setHeight(80);
                    InputHighScore.setVisibility(View.VISIBLE);
                    InputHighScore.setHeight(80);

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

                    SubmitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            stats.setName(finalGameName);
                            stats.setHigh_score(Integer.parseInt(InputHighScore.getText().toString()));
                            stats.setGameType(true);
//                            stats.setKills(Integer.parseInt(InputKills.getText().toString()));
//                            stats.setDeaths(Integer.parseInt(InputDeaths.getText().toString()));
//                            stats.setAssists(Integer.parseInt(InputAssists.getText().toString()));
//                            stats.setWins(Integer.parseInt(InputWins.getText().toString()));
//                            stats.setLost(Integer.parseInt(InputLost.getText().toString()));

                            StatsDatabase db = new StatsDatabase(getContext());
                            db.addStats(stats);
                            db.close();
                            Navigation.findNavController(view).popBackStack();
                        }
                    });
                }
            }
        });
        return view;
    }
}