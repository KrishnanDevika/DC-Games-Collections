package com.example.dcgamescollection.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Author: Devika Krishnan
 * Date: Mar 28, 2022
 * Class: Games.class
 */
public class Games  implements Parcelable {

    private String name;
    private String releaseDate;
    private String gameIcon;
    private double rating;
    private String esrbRating;
    private int playtime;
    private String[] platforms;
    private String[] tags;
    private String[] genres;
    private String[] stores;
    private ArrayList<String> screenShots;

    public Games() {
    }

    //Constructor


    public Games(String name, String releaseDate, String gameIcon, double rating, String esrbRating, int playtime, String[] platforms, String[] tags, String[] genres, String[] stores, ArrayList<String> screenShots) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.gameIcon = gameIcon;
        this.rating = rating;
        this.esrbRating = esrbRating;
        this.playtime = playtime;
        this.platforms = platforms;
        this.tags = tags;
        this.genres = genres;
        this.stores = stores;
        this.screenShots = screenShots;
    }

    public Games(String name, String releaseDate, String gameIcon, double rating) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.gameIcon = gameIcon;
        this.rating = rating;
    }

    protected Games(Parcel in) {
        name = in.readString();
        releaseDate = in.readString();
        gameIcon = in.readString();
        rating = in.readDouble();
    }

    public static final Creator<Games> CREATOR = new Creator<Games>() {
        @Override
        public Games createFromParcel(Parcel in) {
            return new Games(in);
        }

        @Override
        public Games[] newArray(int size) {
            return new Games[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(String gameIcon) {
        this.gameIcon = gameIcon;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getStores() {
        return stores;
    }

    public void setStores(String[] stores) {
        this.stores = stores;
    }

    public ArrayList<String> getScreenShots() {
        return screenShots;
    }

    public void setScreenShots(ArrayList<String> screenShots) {
        this.screenShots = screenShots;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(releaseDate);
        parcel.writeString(gameIcon);
        parcel.writeDouble(rating);
    }
}
