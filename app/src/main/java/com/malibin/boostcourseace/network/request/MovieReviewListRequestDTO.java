package com.malibin.boostcourseace.network.request;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */
public class MovieReviewListRequestDTO {
    private int movieId;
    private String limit;
    private Integer startIndex;
    private Integer length;

    public MovieReviewListRequestDTO(int movieId, String limit, Integer startIndex, Integer length) {
        this.movieId = movieId;
        this.limit = limit;
        this.startIndex = startIndex;
        this.length = length;
    }

    public String toParams() {
        String base = "?id=" + movieId;
        if (limit != null) {
            return base + "&limit=" + limit;
        }
        return base + "&startIndex=" + startIndex + "&length=" + length;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getLimit() {
        return limit;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public Integer getLength() {
        return length;
    }
}
