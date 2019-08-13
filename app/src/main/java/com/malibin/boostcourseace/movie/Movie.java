package com.malibin.boostcourseace.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.malibin.boostcourseace.util.MovieRate;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */

public class Movie implements Parcelable {

    private int imageUrl; //나중에 String 으로 바꿀 것
    private String title;
    private MovieRate movieRate;
    private String openingDay;
    private String genre;
    private String showTime;

    private int likeCount;
    private int dislikeCount;

    private float reservationRate;
    private float starRate;
    private float accumulatedAttendance;

    private String plot;
    private String director;
    private String actress;

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(int imageUrl, String title, MovieRate movieRate, String openingDay, String genre, String showTime, int likeCount, int dislikeCount, float reservationRate, float starRate, float accumulatedAttendance, String plot, String director, String actress) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.movieRate = movieRate;
        this.openingDay = openingDay;
        this.genre = genre;
        this.showTime = showTime;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.reservationRate = reservationRate;
        this.starRate = starRate;
        this.accumulatedAttendance = accumulatedAttendance;
        this.plot = plot;
        this.director = director;
        this.actress = actress;
    }

    protected Movie(Parcel in) {
        imageUrl = in.readInt();
        title = in.readString();
        movieRate = (MovieRate) in.readValue(MovieRate.class.getClassLoader());
        openingDay = in.readString();
        genre = in.readString();
        showTime = in.readString();
        likeCount = in.readInt();
        dislikeCount = in.readInt();
        reservationRate = in.readFloat();
        starRate = in.readFloat();
        accumulatedAttendance = in.readFloat();
        plot = in.readString();
        director = in.readString();
        actress = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageUrl);
        dest.writeString(title);
        dest.writeValue(movieRate);
        dest.writeString(openingDay);
        dest.writeString(genre);
        dest.writeString(showTime);
        dest.writeInt(likeCount);
        dest.writeInt(dislikeCount);
        dest.writeFloat(reservationRate);
        dest.writeFloat(starRate);
        dest.writeFloat(accumulatedAttendance);
        dest.writeString(plot);
        dest.writeString(director);
        dest.writeString(actress);
    }

    public int getImageUrl() {
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

    public String getShowTime() {
        return showTime;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
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
}
