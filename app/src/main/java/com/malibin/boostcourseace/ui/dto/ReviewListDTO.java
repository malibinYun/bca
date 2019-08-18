package com.malibin.boostcourseace.ui.dto;

import com.malibin.boostcourseace.ui.review.MovieReview;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */

public class ReviewListDTO {
    private int totalCount;
    private List<MovieReview> reviews;

    public ReviewListDTO(int totalCount, List<MovieReview> reviews) {
        this.totalCount = totalCount;
        this.reviews = reviews;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<MovieReview> getReviews() {
        return reviews;
    }
}
