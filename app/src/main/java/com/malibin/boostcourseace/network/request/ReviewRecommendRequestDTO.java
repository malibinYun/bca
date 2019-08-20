package com.malibin.boostcourseace.network.request;

/**
 * Created By Yun Hyeok
 * on 8월 20, 2019
 */
public class ReviewRecommendRequestDTO {
    private int reviewId;
    private String writer;

    public ReviewRecommendRequestDTO(int reviewId) {
        this.reviewId = reviewId;
        this.writer = "모메";
    }

    public String toParams() {
        return "?review_id=" + reviewId + "&writer=" + writer;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getWriter() {
        return writer;
    }
}
