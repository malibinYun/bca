package com.malibin.boostcourseace.network.response;

import com.malibin.boostcourseace.review.MovieReview;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */
public class MovieReviewResponseDTO {
    private int id;
    private String writer;
    private int movieId;
    private String writer_image;
    private String time;
    private long timestamp;
    private float rating;
    private String contents;
    private int recommend;

    public MovieReviewResponseDTO(int id, String writer, int movieId, String writer_image, String time, long timestamp, float rating, String contents, int recommend) {
        this.id = id;
        this.writer = writer;
        this.movieId = movieId;
        this.writer_image = writer_image;
        this.time = time;
        this.timestamp = timestamp;
        this.rating = rating;
        this.contents = contents;
        this.recommend = recommend;
    }

    public MovieReview toMovieReview() {
        return new MovieReview(id, writer_image, writer, time, rating, contents, recommend);
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getWriter_image() {
        return writer_image;
    }

    public String getTime() {
        return time;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getRating() {
        return rating;
    }

    public String getContents() {
        return contents;
    }

    public int getRecommend() {
        return recommend;
    }
}
