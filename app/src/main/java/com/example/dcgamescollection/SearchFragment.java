package com.example.dcgamescollection;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
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
import com.example.dcgamescollection.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ACTION_TYPE = "action_type";
    public static final String ADD_TO_COLLECTION = "save";
    private SearchView searchView;
    private RecyclerView gameRecyclerView;
    private CustomSearchAdapterView adapterView;
    private ArrayList<Games> gamesList;
    SharedPreferences sharedPreferences;
    int genre;
    int platforms;
    String url;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
         View view = inflater.inflate(R.layout.fragment_search, container, false);
         searchView = view.findViewById(R.id.gameSearch);
         gameRecyclerView = view.findViewById(R.id.GamesList);
         gamesList = new ArrayList<>();
         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 Log.d("ResultSearch", query);
                 searchView.clearFocus();
                 getData(query);
                 return false;
             }
             @Override
             public boolean onQueryTextChange(String newText) {
                 gameRecyclerView.setAdapter(null);
                 gamesList.clear();
                 return false;
             }

         });
        return view;
    }

   private void getData(String searchValue){
       Games game = new Games();
       String API_KEY = Const.API_KEY;
       searchValue  = searchValue.replace(" ", "%20");

       sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
       String platform = sharedPreferences.getString("platformFilter","PC" );
       String genres = sharedPreferences.getString("genreFilter", "Action");

       boolean isParentMode = sharedPreferences.getBoolean("parent", false) ;

       if(platform.equals("PC")){
           platforms = 4;
       }
       if(platform.equals("iOS")){
           platforms = 3;
       }
       if(platform.equals("macOS") ){
           platforms = 5;
       }
       if(platform.equals("Android") ){
           platforms = 21;
       }
       if(platform.equals("Playstation") ){
           platforms = 10;
       }
       if(platform.equals("Xbox One")){
           platforms = 1;
       }
       if(platform.equals("Xbox 360")){
           platforms = 14;
       }
       if(platform.equals("Linux")){
           platforms = 6;
       }
       if(platform.equals("Nintendo Switch")){
           platforms = 7;
       }

       if(genres.equals("Action")){
           genre = 4;
       }
       if(genres.equals("Adventure")){
           genre = 3;
       }
       if(genres.equals("Strategy")){
           genre = 10;
       }
       if(genres.equals("Shooter")){
           genre = 2;
       }
       if(genres.equals("Racing")){
           genre = 1;
       }
       if(genres.equals("Sports")){
           genre = 15;
       }
       if(genres.equals("Puzzle")){
           genre = 7;
       }
       if(genres.equals("Educational")){
           genre = 34;
       }
       if(genres.equals("RPG")){
           genre = 5;
       }

       if(isParentMode){
           url ="https://api.rawg.io/api/platforms?key="+Const.API_KEY+"&parents";
       }
       if(!searchValue.equals("")){
           url = ("https://api.rawg.io/api/games?key="+API_KEY+"&search="+searchValue);

       }else {
           url = "https://api.rawg.io/api/games?key="+API_KEY+"&platforms="+platforms+"&genres="+genre;

       }

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

                       Bundle extra = new Bundle();
                       extra.putString(ACTION_TYPE, ADD_TO_COLLECTION);
                       adapterView = new CustomSearchAdapterView(gamesList, getContext(), extra);
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

    @Override
    public void onResume() {
        super.onResume();
        getData(searchView.getQuery().toString());
    }

}