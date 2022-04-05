package com.example.dcgamescollection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dcgamescollection.Api.GameSingleton;
import com.example.dcgamescollection.Pojo.Games;
import com.example.dcgamescollection.RecyclerView.CustomSearchAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingGamesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingGamesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView gameRecyclerView;
    private CustomSearchAdapterView adapterView;
    private ArrayList<Games> gamesList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpcomingGamesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingGamesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingGamesFragment newInstance(String param1, String param2) {
        UpcomingGamesFragment fragment = new UpcomingGamesFragment();
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
        View view = inflater.inflate(R.layout.fragment_upcoming_games, container, false);
        gameRecyclerView = view.findViewById(R.id.upcomingList);
        gamesList = new ArrayList<>();
        getData();
        return view;
    }

    private void getData(){
        Games game = new Games();
        String API_KEY = Const.API_KEY;
        //https://api.rawg.io/api/games?key=API_KEY&dates=2010-01-01
        Calendar calendar = Calendar.getInstance();
        //Formats date objects into strings (ex. 2022-04-01 is April 1st, 2022)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Adds 1 to today's calender date (ie. tomorrow's date).
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String tomorrow = dateFormat.format(calendar.getTime());
        //Adds 7 to today's calender date (ie. next week's date).
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        String nextWeek = dateFormat.format(calendar.getTime());

        String url = ("https://api.rawg.io/api/games?key="+API_KEY+"&dates="+tomorrow+","+nextWeek);

        Log.d("ResultSearch", url);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject results = jsonArray.getJSONObject(i);
                        game.setName(results.getString("name"));
                        game.setRating(results.getDouble("rating"));
                        game.setReleaseDate(results.getString("released"));
                        game.setGameIcon(results.getString("background_image"));
                        gamesList.add(new Games(results.getString("name"), results.getString("released"),results.getString("background_image"), results.getDouble("rating")));
                        adapterView = new CustomSearchAdapterView(gamesList, getContext());
                        gameRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        gameRecyclerView.setAdapter(adapterView);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY_ERROR", error.getLocalizedMessage());
            }
        });

        GameSingleton.getInstance(getContext()).getRequestQueue().add(objectRequest);
    }
}