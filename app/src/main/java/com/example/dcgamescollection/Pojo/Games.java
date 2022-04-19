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

    private int id;
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

    /**
     * get data for More Info Fragment
     * @param name
     * @param releaseDate
     * @param gameIcon
     * @param rating
     * @param esrbRating
     * @param playtime
     * @param platforms
     * @param tags
     * @param genres
     * @param stores
     * @param screenShots
     */
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

    /**
     * Read data from database
     * @param id
     * @param name
     * @param releaseDate
     * @param gameIcon
     * @param rating
     */
    public Games( int id, String name, String releaseDate, double rating ,String gameIcon) {
       this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.gameIcon = gameIcon;
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
    public String toString() {
        return name;
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
