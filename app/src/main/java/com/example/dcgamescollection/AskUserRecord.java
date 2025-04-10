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

    public static final String ACTION_TYPE = "action_type";
    public static final int CREATE = 0;
    public static final int UPDATE = 1;

    Games games;
    Stats stats;

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

        TextView NameTitle = view.findViewById(R.id.StatsGameTitle);

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

        if(getArguments().getInt(ACTION_TYPE) == 0) {
            games = getArguments().getParcelable(STATS);
            NameTitle.setText(games.getName());
            SubmitButton.setText("ADD STATS");
            stats = new Stats();
        } else if(getArguments().getInt(ACTION_TYPE) == 1) {
            //games = null;
            stats = getArguments().getParcelable(STATS);
            NameTitle.setText(stats.getName());
            //InputHighScore.setText(stats.getHigh_score());
            SubmitButton.setText("UPDATE STATS");
        }

        String gameName = "";
        //if(getArguments() != null) {
            if (games != null) {
                gameName = games.getName();
            } else {
                gameName = stats.getName();
            }
        //}
        String finalGameName = gameName;
        Log.d("ASK", finalGameName);


        changeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (changeView.isChecked()) {
                    HighScoreTitle.setVisibility(View.GONE);
                    InputHighScore.setVisibility(View.GONE);

                    KillsTitle.setVisibility(View.VISIBLE);
                    InputKills.setVisibility(View.VISIBLE);
                    if((stats.getGameType() == 0) && getArguments().getInt(ACTION_TYPE) == 1) {
                        InputKills.setText(String.valueOf(stats.getKills()));
                    }

                    DeathsTitle.setVisibility(View.VISIBLE);
                    InputDeaths.setVisibility(View.VISIBLE);
                    if((stats.getGameType() == 0) && getArguments().getInt(ACTION_TYPE) == 1) {
                        InputDeaths.setText(String.valueOf(stats.getDeaths()));
                    }

                    AssistsTitle.setVisibility(View.VISIBLE);
                    InputAssists.setVisibility(View.VISIBLE);
                    if((stats.getGameType() == 0) && getArguments().getInt(ACTION_TYPE) == 1) {
                        InputAssists.setText(String.valueOf(stats.getAssists()));
                    }

                    WinsTitle.setVisibility(View.VISIBLE);
                    InputWins.setVisibility(View.VISIBLE);
                    if((stats.getGameType() == 0) && getArguments().getInt(ACTION_TYPE) == 1) {
                        InputWins.setText(String.valueOf(stats.getWins()));
                    }

                    LostTitle.setVisibility(View.VISIBLE);
                    InputLost.setVisibility(View.VISIBLE);
                    if((stats.getGameType() == 0) && getArguments().getInt(ACTION_TYPE) == 1) {
                        InputLost.setText(String.valueOf(stats.getLost()));
                    }

                    SubmitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            stats.setName(finalGameName);
                            stats.setKills(Integer.parseInt(InputKills.getText().toString()));
                            stats.setDeaths(Integer.parseInt(InputDeaths.getText().toString()));
                            stats.setAssists(Integer.parseInt(InputAssists.getText().toString()));
                            stats.setWins(Integer.parseInt(InputWins.getText().toString()));
                            stats.setLost(Integer.parseInt(InputLost.getText().toString()));
                            stats.setGameType(0);

                            StatsDatabase db = new StatsDatabase(getContext());
                            if(getArguments().getInt(ACTION_TYPE) == 0) {
                                db.addStats(stats);
                            } else if(getArguments().getInt(ACTION_TYPE) == 1){
                                db.updateStats(stats);
                            }
                            db.close();
                            Navigation.findNavController(view).popBackStack();
                        }
                    });

                } else {
                    HighScoreTitle.setVisibility(View.VISIBLE);
                    InputHighScore.setVisibility(View.VISIBLE);
                    if((stats.getGameType() == 1) && getArguments().getInt(ACTION_TYPE) == 1) {
                        InputHighScore.setText(String.valueOf(stats.getHigh_score()));
                    }

                    KillsTitle.setVisibility(View.GONE);
                    InputKills.setVisibility(View.GONE);

                    DeathsTitle.setVisibility(View.GONE);
                    InputDeaths.setVisibility(View.GONE);

                    AssistsTitle.setVisibility(View.GONE);
                    InputAssists.setVisibility(View.GONE);

                    WinsTitle.setVisibility(View.GONE);
                    InputWins.setVisibility(View.GONE);

                    LostTitle.setVisibility(View.GONE);
                    InputLost.setVisibility(View.GONE);

                    SubmitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            stats.setName(finalGameName);
                            stats.setHigh_score(Integer.parseInt(InputHighScore.getText().toString()));
                            stats.setGameType(1);
//                            stats.setKills(Integer.parseInt(InputKills.getText().toString()));
//                            stats.setDeaths(Integer.parseInt(InputDeaths.getText().toString()));
//                            stats.setAssists(Integer.parseInt(InputAssists.getText().toString()));
//                            stats.setWins(Integer.parseInt(InputWins.getText().toString()));
//                            stats.setLost(Integer.parseInt(InputLost.getText().toString()));

                            StatsDatabase db = new StatsDatabase(getContext());
                            if(getArguments().getInt(ACTION_TYPE) == 0) {
                                db.addStats(stats);
                            } else if(getArguments().getInt(ACTION_TYPE) == 1){
                                db.updateStats(stats);
                            }
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