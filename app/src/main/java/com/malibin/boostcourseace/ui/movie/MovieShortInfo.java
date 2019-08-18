package com.malibin.boostcourseace.ui.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.malibin.boostcourseace.util.MovieRate;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */
public class MovieShortInfo implements Parcelable {
    private int id;
    private String imageUrl;
    private String title;
    private String titleEng;

    private int reservationRank;
    private float reservationRate;
    private MovieRate movieRate;
    private String openingDay;

    public static final Creator<MovieShortInfo> CREATOR = new Creator<MovieShortInfo>() {
        @Override
        public MovieShortInfo createFromParcel(Parcel in) {
            return new MovieShortInfo(in);
        }

        @Override
        public MovieShortInfo[] newArray(int size) {
            return new MovieShortInfo[size];
        }
    };

    public MovieShortInfo(int id, String imageUrl, String title, String titleEng, int reservationRank, float reservationRate, MovieRate movieRate, String openingDay) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.titleEng = titleEng;
        this.reservationRank = reservationRank;
        this.reservationRate = reservationRate;
        this.movieRate = movieRate;
        this.openingDay = openingDay;
    }

    protected MovieShortInfo(Parcel in) {
        id = in.readInt();
        imageUrl = in.readString();
        title = in.readString();
        titleEng = in.readString();
        reservationRank = in.readInt();
        reservationRate = in.readFloat();
        movieRate = (MovieRate) in.readValue(MovieRate.class.getClassLoader());
        openingDay = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeString(titleEng);
        dest.writeInt(reservationRank);
        dest.writeFloat(reservationRate);
        dest.writeValue(movieRate);
        dest.writeString(openingDay);
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

    public String getTitleEng() {
        return titleEng;
    }

    public int getReservationRank() {
        return reservationRank;
    }

    public float getReservationRate() {
        return reservationRate;
    }

    public MovieRate getMovieRate() {
        return movieRate;
    }

    public String getOpeningDay() {
        return openingDay;
    }
}
