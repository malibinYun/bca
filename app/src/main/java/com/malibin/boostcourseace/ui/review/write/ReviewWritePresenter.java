package com.malibin.boostcourseace.ui.review.write;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.network.CallBack;
import com.malibin.boostcourseace.network.MovieRepository;
import com.malibin.boostcourseace.network.request.MovieReviewSaveRequestDTO;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */
public class ReviewWritePresenter implements ReviewWriteContract.Presenter {

    private ReviewWriteContract.View view;
    private MovieRepository repository;

    public ReviewWritePresenter(
            @NonNull ReviewWriteContract.View view,
            @NonNull MovieRepository repository
    ) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }

    @Override
    public void saveReview(MovieReviewSaveRequestDTO dto) {
        repository.sendReviewSaveRequest(dto, new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                view.notifyReviewSaved();
                Log.d("Malibin Debug", response);
            }

            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }
}
