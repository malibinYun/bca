package com.malibin.boostcourseace.network.response;

import com.malibin.boostcourseace.ui.entity.Movie;
import com.malibin.boostcourseace.util.MovieRate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */

public class MovieDetailResponseDTO {
    private int id;
    private String title;               // 제목
    private String date;                // 개봉연월일
    private float user_rating;          // 사용자 평점
    private float audience_rating;      // 관람객 평점
    private float reviewer_rating;      // 기자 평론가 평점
    private float reservation_rate;     // 예매율
    private int reservation_grade;      // 예매율 순위

    private int grade;                  // 관람등급 12 /15 /19
    private String thumb;               // 썸네일 이미지 링크
    private String image;               // 원본 이미지 링크
    private String photos;              // 포토 링크
    private String videos;              // 동영상 링크
    private String outlinks;            // 외부 링크

    private String genre;               // 장르
    private int duration;               // 러닝타임
    private int audience;               // 누적관객수
    private String synopsis;            // 줄거리
    private String director;            // 감독
    private String actor;               // 출연배우
    private int like;                   // 좋아요 총 수
    private int dislike;                // 싫어요 총 수

    public MovieDetailResponseDTO(int id, String title, String date, float user_rating, float audience_rating, float reviewer_rating, float reservation_rate, int reservation_grade, int grade, String thumb, String image, String photos, String videos, String outlinks, String genre, int duration, int audience, String synopsis, String director, String actor, int like, int dislike) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.user_rating = user_rating;
        this.audience_rating = audience_rating;
        this.reviewer_rating = reviewer_rating;
        this.reservation_rate = reservation_rate;
        this.reservation_grade = reservation_grade;
        this.grade = grade;
        this.thumb = thumb;
        this.image = image;
        this.photos = photos;
        this.videos = videos;
        this.outlinks = outlinks;
        this.genre = genre;
        this.duration = duration;
        this.audience = audience;
        this.synopsis = synopsis;
        this.director = director;
        this.actor = actor;
        this.like = like;
        this.dislike = dislike;
    }

    public Movie toMovie() {
        List<String> emptyList = Collections.emptyList();
        List<String> photoList = photos != null ? Arrays.asList(photos.split(",")) : emptyList;
        List<String> videoList = videos != null ? Arrays.asList(videos.split(",")) : emptyList;
        List<String> outlinkList = outlinks != null ? Arrays.asList(outlinks.split(",")) : emptyList;
        MovieRate movieRate = MovieRate.findByRate(grade);
        return new Movie(id, image, title, movieRate, date, genre, duration, like, dislike, reservation_grade
                , reservation_rate, audience_rating, audience, synopsis, director, actor, photoList, videoList, outlinkList);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public float getUser_rating() {
        return user_rating;
    }

    public float getAudience_rating() {
        return audience_rating;
    }

    public float getReviewer_rating() {
        return reviewer_rating;
    }

    public float getReservation_rate() {
        return reservation_rate;
    }

    public int getReservation_grade() {
        return reservation_grade;
    }

    public int getGrade() {
        return grade;
    }

    public String getThumb() {
        return thumb;
    }

    public String getImage() {
        return image;
    }

    public String getPhotos() {
        return photos;
    }

    public String getVideos() {
        return videos;
    }

    public String getOutlinks() {
        return outlinks;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public int getAudience() {
        return audience;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }
}
