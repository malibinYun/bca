package com.malibin.boostcourseace.ui.entity;

/**
 * Created By Yun Hyeok
 * on 8월 30, 2019
 */

public class MovieVideoGallery extends MovieGallery {

    private String videoUrl;
    private String imageUrl;

    public MovieVideoGallery(String videoUrl) {
        this.videoUrl = videoUrl;
        this.imageUrl = convertThumbnailUrl(videoUrl);
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    private String convertThumbnailUrl(String url) {
        String key = url.replace("https://youtu.be/", "");
        return "https://img.youtube.com/vi/" + key + "/0.jpg";
    }
}
