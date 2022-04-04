package com.example.dcgamescollection.Api;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Author: Devika Krishnan
 * Date: Mar 28, 2022
 * Class: GameSingleton
 */
public class GameSingleton {
    public static GameSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    //Constructor
    private GameSingleton(Context context){
        this.context = context;
    }

    //GetInstance
    public static GameSingleton getInstance(Context context){
        if(instance == null){
            instance = new GameSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }


}
