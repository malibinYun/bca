package com.malibin.boostcourseace.ui.movie;

import android.text.TextUtils;

import com.malibin.boostcourseace.util.MovieRate;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */

public class Movie {
    private int id;
    private String imageUrl;
    private String title;
    private MovieRate movieRate;
    private String openingDay;
    private String genre;
    private int showTime;

    private int likeCount;
    private int dislikeCount;

    private int reservationRank;
    private float reservationRate;
    private float starRate;
    private float accumulatedAttendance;

    private String plot;
    private String director;
    private String actress;

    private List<String> photoLinks;
    private List<String> videoLinks;
    private List<String> outLinks;

    public Movie(int id, String imageUrl, String title, MovieRate movieRate, String openingDay, String genre, int showTime, int likeCount, int dislikeCount, int reservationRank, float reservationRate, float starRate, float accumulatedAttendance, String plot, String director, String actress, List<String> photoLinks, List<String> videoLinks, List<String> outLinks) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.movieRate = movieRate;
        this.openingDay = openingDay;
        this.genre = genre;
        this.showTime = showTime;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.reservationRank = reservationRank;
        this.reservationRate = reservationRate;
        this.starRate = starRate;
        this.accumulatedAttendance = accumulatedAttendance;
        this.plot = plot;
        this.director = director;
        this.actress = actress;
        this.photoLinks = photoLinks;
        this.videoLinks = videoLinks;
        this.outLinks = outLinks;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", movieRate=" + movieRate +
                ", openingDay='" + openingDay + '\'' +
                ", genre='" + genre + '\'' +
                ", showTime=" + showTime +
                ", likeCount=" + likeCount +
                ", dislikeCount=" + dislikeCount +
                ", reservationRank=" + reservationRank +
                ", reservationRate=" + reservationRate +
                ", starRate=" + starRate +
                ", accumulatedAttendance=" + accumulatedAttendance +
                ", plot='" + plot + '\'' +
                ", director='" + director + '\'' +
                ", actress='" + actress + '\'' +
                ", photoLinks=" + photoLinks +
                ", videoLinks=" + videoLinks +
                ", outLinks=" + outLinks +
                '}';
    }

    public Object[] toSqlArgs() {
        return new Object[]{id, imageUrl, title, movieRate.getRate(), openingDay, genre, showTime,
                likeCount, dislikeCount, reservationRank, reservationRate, starRate, accumulatedAttendance, plot, director, actress,
                TextUtils.join("##", photoLinks), TextUtils.join("##", videoLinks), TextUtils.join("##", outLinks)
        };
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public MovieRate getMovieRate() {
        return movieRate;
    }

    public String getOpeningDay() {
        return openingDay;
    }

    public String getGenre() {
        return genre;
    }

    public int getShowTime() {
        return showTime;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public int getReservationRank() {
        return reservationRank;
    }

    public float getReservationRate() {
        return reservationRate;
    }

    public float getStarRate() {
        return starRate;
    }

    public float getAccumulatedAttendance() {
        return accumulatedAttendance;
    }

    public String getPlot() {
        return plot;
    }

    public String getDirector() {
        return director;
    }

    public String getActress() {
        return actress;
    }

    public List<String> getPhotoLinks() {
        return photoLinks;
    }

    public List<String> getVideoLinks() {
        return videoLinks;
    }

    public List<String> getOutLinks() {
        return outLinks;
    }
}
