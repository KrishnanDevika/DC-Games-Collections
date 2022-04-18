package com.example.dcgamescollection.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Stats implements Parcelable{

    private int id;
    private String name;
    private boolean gameType;
    private int high_score;
    private int kills;
    private int deaths;
    private int assists;
    private int wins;
    private int lost;

    public Stats() {
    }

    public Stats(int id, String name, int high_score, int kills, int deaths, int assists, int wins, int lost) {
        this.id = id;
        this.name = name;
        this.high_score = high_score;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.wins = wins;
        this.lost = lost;
    }

    public Stats(String name, int high_score) {
        this.name = name;
        this.high_score = high_score;
        this.gameType = true;
    }

    public Stats(String name, int kills, int deaths, int assists, int wins, int lost) {
        this.name = name;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.wins = wins;
        this.lost = lost;
        this.gameType = false;
    }

    @Override
    public String toString() {
        return name;
    }

    protected Stats(Parcel in) {
        id = in.readInt();
        name = in.readString();
        high_score = in.readInt();
        kills = in.readInt();
        deaths = in.readInt();
        assists = in.readInt();
        wins = in.readInt();
        lost = in.readInt();
    }

    public static final Creator<Stats> CREATOR = new Creator<Stats>() {
        @Override
        public Stats createFromParcel(Parcel in) {
            return new Stats(in);
        }

        @Override
        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHigh_score() {
        return high_score;
    }

    public void setHigh_score(int high_score) {
        this.high_score = high_score;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public boolean getGameType() {
        return gameType;
    }

    public void setGameType(boolean gameType) {
        this.gameType = gameType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(high_score);
        parcel.writeInt(kills);
        parcel.writeInt(deaths);
        parcel.writeInt(assists);
        parcel.writeInt(wins);
        parcel.writeInt(lost);
    }
}
