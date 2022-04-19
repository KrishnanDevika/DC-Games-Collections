package com.example.dcgamescollection.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dcgamescollection.Pojo.Stats;

import java.util.ArrayList;

public class StatsDatabase extends SQLiteOpenHelper {

    //DataBase Version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "statsdatabase";

    //Database Name
    public static final String TABLE_STATS = "stats";

    //Database column Names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GAME_NAME = "name";
    public static final String COLUMN_GAME_TYPE = "game_type";
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
            COLUMN_GAME_TYPE        + " INTEGER, " +
            COLUMN_HIGH_SCORE       + " INTEGER, " +
            COLUMN_PLAYER_KILLS     + " INTEGER, " +
            COLUMN_PLAYER_DEATHS    + " INTEGER, " +
            COLUMN_PLAYER_ASSISTS   + " INTEGER, " +
            COLUMN_ROUND_WIN        + " INTEGER, " +
            COLUMN_ROUND_LOST       + " INTEGER)";

//Insert Data in Table Query
    public void addStats(Stats stats){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_GAME_NAME, stats.getName());
        values.put(COLUMN_GAME_TYPE, stats.getGameType());
        values.put(COLUMN_HIGH_SCORE, stats.getHigh_score());
        values.put(COLUMN_PLAYER_KILLS, stats.getKills());
        values.put(COLUMN_PLAYER_DEATHS, stats.getDeaths());
        values.put(COLUMN_PLAYER_ASSISTS, stats.getAssists());
        values.put(COLUMN_ROUND_WIN, stats.getWins());
        values.put(COLUMN_ROUND_LOST, stats.getLost());

        db.insert(TABLE_STATS, null, values);
        db.close();
    }

//Read/Get Data from Table
    public Stats getStats(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Stats stats = null;
        Cursor cursor = db.query(TABLE_STATS,
                new String[]{COLUMN_ID, COLUMN_GAME_NAME, COLUMN_GAME_TYPE, COLUMN_HIGH_SCORE, COLUMN_PLAYER_KILLS,
                        COLUMN_PLAYER_DEATHS, COLUMN_PLAYER_ASSISTS, COLUMN_ROUND_WIN, COLUMN_ROUND_LOST}, COLUMN_ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if(cursor.moveToFirst()){
            stats = new Stats(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8)
            );
        }
        db.close();
        return stats;
    }

//Get all Data from table
    public ArrayList<Stats> getAllStats(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Stats> stats = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STATS , null);
        while (cursor.moveToNext()){
            stats.add(new Stats(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8)
            ));
        }
        db.close();
        return stats;
    }

//Update Stats Query
    public int updateStats(Stats stats){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_GAME_NAME, stats.getName());
        values.put(COLUMN_GAME_TYPE, stats.getGameType());
        values.put(COLUMN_HIGH_SCORE, stats.getHigh_score());
        values.put(COLUMN_PLAYER_KILLS, stats.getKills());
        values.put(COLUMN_PLAYER_DEATHS, stats.getDeaths());
        values.put(COLUMN_PLAYER_ASSISTS, stats.getAssists());
        values.put(COLUMN_ROUND_WIN, stats.getWins());
        values.put(COLUMN_ROUND_LOST, stats.getLost());

        return db.update(TABLE_STATS, values, COLUMN_ID + "=?", new String[]{String.valueOf(stats.getId())});
    }

//Delete Stats Query 
    public void deleteStats(int stats){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATS, COLUMN_ID + "=?", new String[]{String.valueOf(stats)});
        db.close();
    }

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
