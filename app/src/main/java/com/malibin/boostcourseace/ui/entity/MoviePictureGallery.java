package com.malibin.boostcourseace.ui.entity;

/**
 * Created By Yun Hyeok
 * on 8월 30, 2019
 */

public class MoviePictureGallery extends MovieGallery{

    private String imageUrl;

    public MoviePictureGallery(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
