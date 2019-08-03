package com.malibin.boostcourseace.dto;

import com.malibin.boostcourseace.util.MovieRate;

import java.io.Serializable;

/**
 * Created By Yun Hyeok
 * on 8월 03, 2019
 */

public class ReviewMoreDTO implements Serializable {
    private String title;
    private MovieRate movieRate;
    private float starRate;

    public ReviewMoreDTO(String title, MovieRate movieRate, float starRate) {
        this.title = title;
        this.movieRate = movieRate;
        this.starRate = starRate;
    }

    public String getTitle() {
        return title;
    }

    public MovieRate getMovieRate() {
        return movieRate;
    }

    public float getStarRate() {
        return starRate;
    }

    @Override
    public String toString() {
        return "ReviewMoreDTO{" +
                "title='" + title + '\'' +
                ", movieRate=" + movieRate +
                ", starRate=" + starRate +
                '}';
    }
}
