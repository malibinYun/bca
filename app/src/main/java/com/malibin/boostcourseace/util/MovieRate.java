package com.malibin.boostcourseace.util;

import com.malibin.boostcourseace.R;

import java.util.Arrays;

/**
 * Created By Yun Hyeok
 * on 8월 03, 2019
 */
public enum MovieRate {
    ALL(0, R.drawable.ic_all),
    TWELVE(12, R.drawable.ic_12),
    FIFTEEN(15, R.drawable.ic_15),
    NINETEEN(19, R.drawable.ic_19);

    private int rate;
    private int imageResource;

    MovieRate(int rate, int imageResource) {
        this.rate = rate;
        this.imageResource = imageResource;
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
}
