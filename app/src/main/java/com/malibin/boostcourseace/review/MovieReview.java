package com.malibin.boostcourseace.review;

public class MovieReview {
    private String profile;
    private String nickname;
    private String time;
    private float starRate;
    private String content;
    private int recommendCount;

    public MovieReview(String profile, String nickname, String time, float starRate, String content, int recommendCount) {
        this.profile = profile;
        this.nickname = nickname;
        this.time = time;
        this.starRate = starRate;
        this.content = content;
        this.recommendCount = recommendCount;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "profile='" + profile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", time='" + time + '\'' +
                ", starRate=" + starRate +
                ", content='" + content + '\'' +
                ", recommendCount=" + recommendCount +
                '}';
    }

    public String getProfile() {
        return profile;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTime() {
        return time;
    }

    public float getStarRate() {
        return starRate;
    }

    public String getContent() {
        return content;
    }

    public int getRecommendCount() {
        return recommendCount;
    }
}
