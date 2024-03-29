package com.malibin.boostcourseace.ui.review.write;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.network.CallBack;
import com.malibin.boostcourseace.network.RemoteRepository;
import com.malibin.boostcourseace.network.request.MovieReviewSaveRequestDTO;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */

public class ReviewWritePresenter implements ReviewWriteContract.Presenter {

    private ReviewWriteContract.View view;
    private RemoteRepository remoteRepository;

    public ReviewWritePresenter(
            @NonNull ReviewWriteContract.View view,
            @NonNull RemoteRepository remoteRepository
    ) {
        this.view = view;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void saveReview(MovieReviewSaveRequestDTO dto) {
        view.setSaveBtnEnable(false);
        remoteRepository.sendReviewSaveRequest(dto, new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                view.notifyReviewSaved();
            }

            @Override
            public void onFailure(VolleyError error) {
                view.showServerFailToast();
                view.setSaveBtnEnable(true);
            }
        });
    }
}
