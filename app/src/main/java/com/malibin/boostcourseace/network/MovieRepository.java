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

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */
public class MovieRepository {

    private static MovieRepository INSTANCE;
    public static RequestQueue requestQueue;

    private Gson gson = new Gson();

    public static MovieRepository getInstance(Context context) {

        requestQueue = Volley.newRequestQueue(context);

        return new MovieRepository();
    }

    public <T> void sendRequest(
            Map<String, String> params,
            CallBack<T> callBack

    ) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://boostcourse-appapi.connect.or.kr:10000/movie/readMovieList",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Log.d("Malibin Debug", "response : " + response);

                        //0~75 , 맨마지막 지워보자
                        String str = response;
                        str = str.substring(0, str.length() - 1);//마지막지움
                        str = str.substring(76);

                        Log.d("Malibin Debug", "str : " + str);

                        T responseDTO = gson.fromJson(str, new TypeToken<T>() {
                        }.getType());

//                        Type tType = new TypeToken<T>(){}.getType();
//                        ResponseTemplate<T> responseDTO = gson.fromJson(response, TypeToken.getParameterized(ResponseTemplate.class, tType).getType());

                        Log.d("Malibin Debug", "responseDTO : " + responseDTO.toString());


                        callBack.onResponse(responseDTO);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Malibin Debug", "error : " + error);
                        callBack.onFailure(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);

    }

    private void processResponse(String response) {
        Gson gson = new Gson();

        MovieDetailResponseDTO movieDetailResponseDTO = gson.fromJson(response, MovieDetailResponseDTO.class);


    }

}
