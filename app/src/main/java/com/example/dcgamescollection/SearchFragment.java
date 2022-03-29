package com.example.dcgamescollection;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dcgamescollection.Api.GameSingleton;
import com.example.dcgamescollection.Pojo.Games;
import com.example.dcgamescollection.RecyclerView.CustomSearchAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    private SearchView searchView;
    private RecyclerView gameRecyclerView;
    private CustomSearchAdapterView adapterView;
    private ArrayList<Games> gamesList;


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
                 getData(query);
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 return false;
             }

         });
        return view;
    }

   private void getData(String searchValue){
       Games game = new Games();
       String API_KEY = "";
       searchValue  = searchValue.replace(" ", "%20");
       String url = ("https://api.rawg.io/api/games?key="+API_KEY+"&search="+searchValue);

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
                       gamesList.add(game);
                       Log.d("Game Name" , game.getName());
                       Log.d("Game Release" , game.getReleaseDate());
                       Log.d("Game rating" , String.valueOf(game.getRating()));
                       Log.d("Game icon" , game.getGameIcon());
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