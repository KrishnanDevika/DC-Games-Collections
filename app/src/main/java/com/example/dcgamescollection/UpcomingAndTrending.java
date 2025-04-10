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
import com.example.dcgamescollection.SearchRecyclerView.CustomSearchAdapterView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingAndTrending#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingAndTrending extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ADD_TO_CALENDAR = "add";
    public static final String ADD_TO_COLLECTION = "save";
    public static final String ACTION_TYPE = "action_type";
    private RecyclerView gameRecyclerView;
    private CustomSearchAdapterView adapter;
    private ArrayList<Games> gamesList;
    private static int tabNum;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpcomingAndTrending() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingAndTrending.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingAndTrending newInstance(String param1, String param2) {
        UpcomingAndTrending fragment = new UpcomingAndTrending();
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
        View view = inflater.inflate(R.layout.fragment_upcoming_and_trending, container, false);

        TabLayout tabs = view.findViewById(R.id.tabLayout);

        gameRecyclerView = view.findViewById(R.id.upcomingTrendingRecycler);

        gamesList = new ArrayList<>();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                gamesList.clear();
                if(tabs.getSelectedTabPosition() == 0) {
                    tabNum = 0;
                    getData(0);
                } else {
                    tabNum = 1;
                    getData(1);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                gamesList.clear();
                if(tabs.getSelectedTabPosition() == 0) {
                    tabNum = 0;
                    getData(0);
                } else {
                    tabNum = 1;
                    getData(1);
                }
            }
        });
        tabs.selectTab(tabs.getTabAt(0));
        return view;
    }

    public void getData(int collection) {

        Games game = new Games();
        String API_KEY = Const.API_KEY;
        String url;

        if (collection == 0) {
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
            url = ("https://api.rawg.io/api/games?key=" + API_KEY + "&dates=" + tomorrow + "," + nextWeek);
        } else {

            //Cannot directly search via url; requires search from function.
            url = ("https://api.rawg.io/api/games?key="+API_KEY);
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject results = jsonArray.getJSONObject(i);
                        //Filter games from all games based on its rating.
                        if (collection == 0 || results.getDouble("rating") >= 4.0) {
                            game.setName(results.getString("name"));
                            game.setRating(results.getDouble("rating"));
                            game.setReleaseDate(results.getString("released"));
                            game.setGameIcon(results.getString("background_image"));
                            game.setPlaytime(results.getInt("playtime"));

                            if(results.isNull("esrb_rating")){
                                game.setEsrbRating("Not Rated");
                            }else{
                                JSONObject esrbObject = results.getJSONObject("esrb_rating");
                                game.setEsrbRating(esrbObject.getString("name"));
                            }

                            ArrayList<String> tagList = new ArrayList<>();
                            if(results.isNull("tags")){
                                tagList.add("Not Available");
                            }else {
                                JSONArray tags = results.getJSONArray("tags");
                                for (int t = 0; t < tags.length(); t++) {
                                    JSONObject tagNames = tags.getJSONObject(t);
                                    tagList.add(tagNames.getString("name"));
                                }
                            }
                            game.setTags(tagList.toArray(new String[0]));

                            ArrayList<String> platformList = new ArrayList<>();
                            if(results.isNull("platforms")){
                                platformList.add("Not Available");
                            }else {
                                JSONArray platforms = results.getJSONArray("platforms");
                                for (int p = 0; p < platforms.length(); p++) {
                                    JSONObject platformNames = platforms.getJSONObject(p);
                                    JSONObject platform = platformNames.getJSONObject("platform");
                                    platformList.add(platform.getString("name"));
                                }
                            }
                            game.setPlatforms(platformList.toArray(new String[0]));

                            ArrayList<String> genreList = new ArrayList<>();
                            if(results.isNull("genres")){
                                genreList.add("Not Available");
                            }else {
                                JSONArray genres = results.getJSONArray("genres");
                                for (int t = 0; t < genres.length(); t++) {
                                    JSONObject genreNames = genres.getJSONObject(t);
                                    genreList.add(genreNames.getString("name"));
                                }
                            }
                            game.setGenres(genreList.toArray(new String[0]));

                            ArrayList<String> storeList = new ArrayList<>();
                            if(!results.isNull("stores")) {
                                JSONArray stores = results.getJSONArray("stores");
                                for (int p = 0; p < stores.length(); p++) {
                                    JSONObject storeNames = stores.getJSONObject(p);
                                    JSONObject store = storeNames.getJSONObject("store");
                                    storeList.add(store.getString("name"));
                                }
                            }else{
                                storeList.add("Not Available");
                            }
                            game.setStores(storeList.toArray(new String[0]));

                            ArrayList<String> shotsList = new ArrayList<>();
                            if(results.isNull("short_screenshots")){
                                shotsList.add("Not Available");
                            }else {
                                JSONArray screenShots = results.getJSONArray("short_screenshots");
                                for (int t = 0; t < screenShots.length(); t++) {
                                    JSONObject shots = screenShots.getJSONObject(t);
                                    shotsList.add(shots.getString("image"));
                                }
                            }
                            game.setScreenShots(shotsList);

                            gamesList.add(new Games(game.getName(), game.getReleaseDate(),game.getGameIcon(), game.getRating(), game.getEsrbRating(),
                                    game.getPlaytime(), game.getPlatforms(), game.getTags(), game.getGenres(), game.getStores(), game.getScreenShots()));
                        }

                        if (collection == 0) {
                            Collections.sort(gamesList, new Comparator<Games>() {
                                @Override
                                public int compare(Games game1, Games game2) {
                                    int game1Date = Integer.parseInt(game1.getReleaseDate().replace("-", ""));
                                    int game2Date = Integer.parseInt(game2.getReleaseDate().replace("-", ""));
                                    return game1Date - game2Date;
                                }
                            });
                        } else {
                            //Sorts the games, in descending order of their ratings.
                            Collections.sort(gamesList, new Comparator<Games>() {
                                @Override
                                //Sorts in descending order(highest number goes first)
                                public int compare(Games game1, Games game2) {
                                    if (game1.getRating() < game2.getRating()) {
                                        return 1;
                                    } else if (game1.getRating() == game2.getRating()) {
                                        return 0;
                                    } else {
                                        return -1;
                                    }
                                }
                            });
                        }
                        Bundle extra = new Bundle();
                        switch(collection) {
                            case 0:
                                extra.putString(ACTION_TYPE, ADD_TO_CALENDAR);
                                break;
                            case 1:
                                extra.putString(ACTION_TYPE, ADD_TO_COLLECTION);
                                break;
                            default:
                                break;
                        }

                        adapter = new CustomSearchAdapterView(gamesList, getContext(), extra);
                        gameRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        gameRecyclerView.setAdapter(adapter);
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

    public static int getTab() {
        return tabNum;
    }
}