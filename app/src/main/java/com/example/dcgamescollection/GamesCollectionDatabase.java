package com.example.dcgamescollection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dcgamescollection.Pojo.Games;

import java.util.ArrayList;

/**
 * Author: Devika Krishnan
 * Date: Apr 06, 2022
 * Class: GameCollectionDatabase
 */

public class GamesCollectionDatabase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mygames";

    public static final String TABLE_COLLECTION = "collection";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_ICON = "game_icon";

    public static final String CREATE_COLLECTION_TABLE = "CREATE TABLE " +
            TABLE_COLLECTION + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " + COLUMN_RELEASE_DATE + " TEXT, "  +
            COLUMN_RATING + " TEXT, " + COLUMN_ICON + " TEXT)";


    /**
     * Constructor
     * @param context
     */
    public GamesCollectionDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_COLLECTION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Add game to database
     * @param games
     */
    public void addGames(Games games){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, games.getName());
        values.put(COLUMN_RELEASE_DATE, games.getReleaseDate());
        values.put(COLUMN_ICON, games.getGameIcon());
        values.put(COLUMN_RATING, games.getRating());
        db.insert(TABLE_COLLECTION, null, values);
        db.close();
    }

    /**
     * get all games in database
     * @return ArrayList of games
     */
    public ArrayList<Games> getAllGames(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Games> games = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_COLLECTION, null);
        while(cursor.moveToNext()){
            games.add(new Games(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4)
            ));
        }
        db.close();
        return games;
    }

    /**
     * Delete game from database
     * @param game
     */
    public void deleteGame(int game){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COLLECTION, COLUMN_ID +  "=?",
                new String[]{String.valueOf(game)});
        db.close();
    }
}
