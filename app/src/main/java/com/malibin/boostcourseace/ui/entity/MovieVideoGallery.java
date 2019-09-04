package com.malibin.boostcourseace.ui.entity;

/**
 * Created By Yun Hyeok
 * on 8월 30, 2019
 */

public class MovieVideoGallery extends MovieGallery {

    private String videoUrl;

    public MovieVideoGallery(String videoUrl) {
        super(convertThumbnailUrl(videoUrl));
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    private static String convertThumbnailUrl(String url) {
        String key = url.replace("https://youtu.be/", "");
        return "https://img.youtube.com/vi/" + key + "/0.jpg";
    }
}
