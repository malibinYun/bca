package com.malibin.boostcourseace.util;

/**
 * Created By Yun Hyeok
 * on 7월 26, 2019
 */

public enum LikeState {
    LIKE(0),
    DISLIKE(1),
    NOTHING(2);

    private int state;

    LikeState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}