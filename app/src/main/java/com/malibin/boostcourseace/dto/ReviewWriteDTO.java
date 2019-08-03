package com.malibin.boostcourseace.dto;

import com.malibin.boostcourseace.util.MovieRate;

import java.io.Serializable;

/**
 * Created By Yun Hyeok
 * on 8월 03, 2019
 */

public class ReviewWriteDTO implements Serializable {
    private String title;
    private MovieRate movieRate;

    public ReviewWriteDTO(String title, MovieRate movieRate) {
        this.title = title;
        this.movieRate = movieRate;
    }

    public String getTitle() {
        return title;
    }

    public MovieRate getMovieRate() {
        return movieRate;
    }

    @Override
    public String toString() {
        return "ReviewWriteDTO{" +
                "title='" + title + '\'' +
                ", movieRate=" + movieRate +
                '}';
    }
}
