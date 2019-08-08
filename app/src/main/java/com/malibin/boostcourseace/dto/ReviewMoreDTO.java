package com.malibin.boostcourseace.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.malibin.boostcourseace.util.MovieRate;

/**
 * Created By Yun Hyeok
 * on 8월 03, 2019
 */

public class ReviewMoreDTO implements Parcelable {
    private float starRate;
    private String title;
    private MovieRate movieRate;

    public static final Creator<ReviewMoreDTO> CREATOR = new Creator<ReviewMoreDTO>() {
        @Override
        public ReviewMoreDTO createFromParcel(Parcel in) {
            return new ReviewMoreDTO(in);
        }

        @Override
        public ReviewMoreDTO[] newArray(int size) {
            return new ReviewMoreDTO[size];
        }
    };

    public ReviewMoreDTO(String title, MovieRate movieRate, float starRate) {
        this.starRate = starRate;
        this.title = title;
        this.movieRate = movieRate;
    }

    protected ReviewMoreDTO(Parcel in) {
        starRate = in.readFloat();
        title = in.readString();
        movieRate = (MovieRate) in.readValue(MovieRate.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(starRate);
        dest.writeString(title);
        dest.writeValue(movieRate);
    }

    @Override
    public String toString() {
        return "ReviewMoreDTO{" +
                "starRate='" + starRate + '\'' +
                ", title=" + title +
                ", movieRate=" + movieRate +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public MovieRate getMovieRate() {
        return movieRate;
    }

    public float getStarRate() {
        return starRate;
    }
}
