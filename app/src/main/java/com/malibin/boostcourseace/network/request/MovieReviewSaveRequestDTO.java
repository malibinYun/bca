package com.malibin.boostcourseace.network.request;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */
public class MovieReviewSaveRequestDTO {
    private int movieId;
    private String nickname;
    //private String time;
    private float starRate;
    private String contents;

    public MovieReviewSaveRequestDTO(int movieId, String nickname, float rating, String contents) {
        this.movieId = movieId;
        this.nickname = nickname;
        this.starRate = rating;
        this.contents = contents;
    }

    public String toParams() {
        return "?id=" + movieId + "&writer=" + nickname + "&rating=" + starRate + "&contents=" + contents;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getNickname() {
        return nickname;
    }

    public float getStarRate() {
        return starRate;
    }

    public String getContents() {
        return contents;
    }
}
