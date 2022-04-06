package com.example.dcgamescollection.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StatsDatabase extends SQLiteOpenHelper {

    //DataBase Version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "statsdatabase";

    //Database Name
    public static final String TABLE_STATS = "stats";

    //Database column Names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GAME_NAME = "name";
    public static final String COLUMN_HIGH_SCORE = "high_score";
    public static final String COLUMN_PLAYER_KILLS = "kills";
    public static final String COLUMN_PLAYER_DEATHS = "deaths";
    public static final String COLUMN_PLAYER_ASSISTS = "assists";
    public static final String COLUMN_ROUND_WIN = "wins";
    public static final String COLUMN_ROUND_LOST = "lost";

    //Create Table Query

    public static final String CREATE_STATS_TABLE = "CREATE TABLE " +
            TABLE_STATS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_GAME_NAME        + " TEXT, " +
            COLUMN_HIGH_SCORE       + " INTEGER, " +
            COLUMN_PLAYER_KILLS     + " INTEGER, " +
            COLUMN_PLAYER_DEATHS    + " INTEGER, " +
            COLUMN_PLAYER_ASSISTS   + " INTEGER, " +
            COLUMN_ROUND_WIN        + " INTEGER, " +
            COLUMN_ROUND_LOST       + " INTEGER)";

    public StatsDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
