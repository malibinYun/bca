package com.malibin.boostcourseace.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.malibin.boostcourseace.network.response.MovieDetailResponseDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */
public class MovieRepository {

    private static MovieRepository INSTANCE;
    public static RequestQueue requestQueue;

    public static MovieRepository getInstance(Context context) {

        requestQueue = Volley.newRequestQueue(context);

        return new MovieRepository();
    }

    public void sendRequest() {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "url",
                response -> processResponse(response),
                error -> {

                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

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
