package com.example.dcgamescollection.Pojo;

/**
 * Author: Devika Krishnan
 * Date: Mar 28, 2022
 * Class: Games.class
 */
public class Games {

    private String name;
    private String releaseDate;
    private int gameIcon;
    private double rating;

    //Constructor
    public Games(String name, String releaseDate, int gameIcon, double rating) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.gameIcon = gameIcon;
        this.rating = rating;
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

    public int getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(int gameIcon) {
        this.gameIcon = gameIcon;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
