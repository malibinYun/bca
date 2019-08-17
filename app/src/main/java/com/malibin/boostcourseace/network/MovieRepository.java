package com.malibin.boostcourseace.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.malibin.boostcourseace.movie.MovieShortInfo;
import com.malibin.boostcourseace.network.response.MovieDetailResponseDTO;
import com.malibin.boostcourseace.network.response.MovieShortInfoResponseDTO;
import com.malibin.boostcourseace.network.response.ResponseTemplate;
import com.malibin.boostcourseace.util.CallBack;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */
public class MovieRepository {

    private static MovieRepository INSTANCE;
    private static RequestQueue requestQueue;
    private static Gson gson;

    private final String baseUrl = "http://boostcourse-appapi.connect.or.kr:10000";

    public static MovieRepository getInstance(Context context) {
        if (INSTANCE != null)
            return INSTANCE;
        requestQueue = Volley.newRequestQueue(context);
        gson = new Gson();
        return INSTANCE = new MovieRepository();
    }

    private MovieRepository() {

    }

    public void sendMovieListRequest(
            Map<String, String> params,
            CallBack<List<MovieShortInfo>> callBack
    ) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                baseUrl + "/movie/readMovieList",
                response -> {
                    Type type = new TypeToken<ResponseTemplate<List<MovieShortInfoResponseDTO>>>() {}.getType();
                    ResponseTemplate<List<MovieShortInfoResponseDTO>> responseDTO = gson.fromJson(response, type);
                    List<MovieShortInfo> movieShortInfoList = toMovieShortInfoList(responseDTO.getResult());
                    callBack.onResponse(movieShortInfoList);
                },
                callBack::onFailure
        ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private List<MovieShortInfo> toMovieShortInfoList(List<MovieShortInfoResponseDTO> list) {
        return list.stream()
                .map(MovieShortInfoResponseDTO::toMovieShortInfo)
                .collect(Collectors.toList());
    }

}
