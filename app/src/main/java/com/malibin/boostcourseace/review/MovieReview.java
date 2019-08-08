package com.malibin.boostcourseace.review;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieReview implements Parcelable {
    private String profile;
    private String nickname;
    private String time;
    private float starRate;
    private String content;
    private int recommendCount;

    public static final Creator<MovieReview> CREATOR = new Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel in) {
            return new MovieReview(in);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };

    public MovieReview(String profile, String nickname, String time, float starRate, String content, int recommendCount) {
        this.profile = profile;
        this.nickname = nickname;
        this.time = time;
        this.starRate = starRate;
        this.content = content;
        this.recommendCount = recommendCount;
    }

    protected MovieReview(Parcel in) {
        profile = in.readString();
        nickname = in.readString();
        time = in.readString();
        starRate = in.readFloat();
        content = in.readString();
        recommendCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(profile);
        dest.writeString(nickname);
        dest.writeString(time);
        dest.writeFloat(starRate);
        dest.writeString(content);
        dest.writeInt(recommendCount);
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "profile='" + profile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", time='" + time + '\'' +
                ", starRate=" + starRate +
                ", content='" + content + '\'' +
                ", recommendCount=" + recommendCount +
                '}';
    }

    public String getProfile() {
        return profile;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTime() {
        return time;
    }

    public float getStarRate() {
        return starRate;
    }

    public String getContent() {
        return content;
    }

    public int getRecommendCount() {
        return recommendCount;
    }
}
