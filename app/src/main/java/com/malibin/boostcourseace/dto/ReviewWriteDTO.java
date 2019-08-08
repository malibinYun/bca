package com.malibin.boostcourseace.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.malibin.boostcourseace.util.MovieRate;

/**
 * Created By Yun Hyeok
 * on 8월 03, 2019
 */

public class ReviewWriteDTO implements Parcelable {
    private String title;
    private MovieRate movieRate;

    public static final Creator<ReviewWriteDTO> CREATOR = new Creator<ReviewWriteDTO>() {
        @Override
        public ReviewWriteDTO createFromParcel(Parcel in) {
            return new ReviewWriteDTO(in);
        }

        @Override
        public ReviewWriteDTO[] newArray(int size) {
            return new ReviewWriteDTO[size];
        }
    };

    public ReviewWriteDTO(String title, MovieRate movieRate) {
        this.title = title;
        this.movieRate = movieRate;
    }

    protected ReviewWriteDTO(Parcel in) {
        title = in.readString();
        movieRate = (MovieRate) in.readValue(MovieRate.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeValue(movieRate);
    }

    @Override
    public String toString() {
        return "ReviewWriteDTO{" +
                "title='" + title + '\'' +
                ", movieRate=" + movieRate +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public MovieRate getMovieRate() {
        return movieRate;
    }
}
