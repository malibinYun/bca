package com.malibin.boostcourseace.network;

import com.android.volley.VolleyError;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */

public interface CallBack<T> {

    void onResponse(T response);

    void onFailure(VolleyError error);
}
