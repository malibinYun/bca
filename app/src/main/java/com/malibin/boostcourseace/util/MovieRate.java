package com.malibin.boostcourseace.util;

import com.malibin.boostcourseace.R;

import java.util.Arrays;

/**
 * Created By Yun Hyeok
 * on 8월 03, 2019
 */

public enum MovieRate {
    ALL(0, R.drawable.ic_all, "전체 관람가"),
    TWELVE(12, R.drawable.ic_12, "12세 관람가"),
    FIFTEEN(15, R.drawable.ic_15, "15세 관람가"),
    NINETEEN(19, R.drawable.ic_19, "19세 관람가");

    private int rate;
    private int imageResource;
    private String text;

    MovieRate(int rate, int imageResource, String text) {
        this.rate = rate;
        this.imageResource = imageResource;
        this.text = text;
    }

    public static MovieRate findByRate(int rate) {
        return Arrays.stream(MovieRate.values())
                .filter(movieRate -> movieRate.rate == rate)
                .findFirst()
                .orElse(null);
    }

    public int getRate() {
        return rate;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }
}
