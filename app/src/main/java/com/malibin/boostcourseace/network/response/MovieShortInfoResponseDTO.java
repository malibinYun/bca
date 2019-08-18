package com.malibin.boostcourseace.network.response;

import com.malibin.boostcourseace.ui.movie.MovieShortInfo;
import com.malibin.boostcourseace.util.MovieRate;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */
public class MovieShortInfoResponseDTO {
    private int id;                 // 영화 아이디
    private String title;           // 영화 제목
    private String title_eng;       // 영화 영문 제목
    private String date;            // 개봉 연월일 (yyyy-MM-dd)

    private float user_rating;      // 사용자 평점
    private float audience_rating;  // 관람객 평점
    private float reviewer_rating;  // 기자 평론가 평점
    private float reservation_rate; // 예매율
    private int reservation_grade;  // 예매율 순위

    private int grade;              // 관람등급 12 / 15 / 19
    private String thumb;           // 썸네일 이미지 링크
    private String image;           // 원본 이미지 링크

    public MovieShortInfoResponseDTO(int id, String title, String title_eng, String date, float user_rating, float audience_rating, float reviewer_rating, float reservation_rate, int reservation_grade, int grade, String thumb, String image) {
        this.id = id;
        this.title = title;
        this.title_eng = title_eng;
        this.date = date;
        this.user_rating = user_rating;
        this.audience_rating = audience_rating;
        this.reviewer_rating = reviewer_rating;
        this.reservation_rate = reservation_rate;
        this.reservation_grade = reservation_grade;
        this.grade = grade;
        this.thumb = thumb;
        this.image = image;
    }

    public MovieShortInfo toMovieShortInfo() {
        MovieRate movieRate = MovieRate.findByRate(grade);
        return new MovieShortInfo(
                id, image, title, title_eng, reservation_grade, reservation_rate, movieRate, date);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle_eng() {
        return title_eng;
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
}
