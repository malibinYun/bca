package com.malibin.boostcourseace.moviedetail;

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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getStarRate() {
        return starRate;
    }

    public void setStarRate(float starRate) {
        this.starRate = starRate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }
}
