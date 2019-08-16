package com.malibin.boostcourseace.movie;

import com.malibin.boostcourseace.util.MovieRate;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */
public class MovieShortInfo {
    private String imageUrl;
    private String title;
    private String titleEng;

    private int reservationRank;
    private float reservationRate;
    private MovieRate movieRate;
    private String openingDay;

    public MovieShortInfo(String imageUrl, String title, String titleEng, int reservationRank, float reservationRate, MovieRate movieRate, String openingDay) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.titleEng = titleEng;
        this.reservationRank = reservationRank;
        this.reservationRate = reservationRate;
        this.movieRate = movieRate;
        this.openingDay = openingDay;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public int getReservationRank() {
        return reservationRank;
    }

    public float getReservationRate() {
        return reservationRate;
    }

    public MovieRate getMovieRate() {
        return movieRate;
    }

    public String getOpeningDay() {
        return openingDay;
    }
}
