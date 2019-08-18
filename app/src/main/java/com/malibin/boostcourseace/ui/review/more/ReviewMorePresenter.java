package com.malibin.boostcourseace.ui.review.more;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.network.CallBack;
import com.malibin.boostcourseace.network.MovieRepository;
import com.malibin.boostcourseace.network.request.MovieReviewListRequestDTO;
import com.malibin.boostcourseace.ui.dto.ReviewListDTO;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */

public class ReviewMorePresenter implements ReviewMoreContract.Presenter {

    private ReviewMoreContract.View view;
    private MovieRepository repository;

    public ReviewMorePresenter(
            @NonNull ReviewMoreContract.View view,
            @NonNull MovieRepository repository
    ) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }

    @Override
    public void sendReviewListRequest(int movieId, int startIdx, int length) {
        view.setLoadingIndicator(true);
        MovieReviewListRequestDTO dto = new MovieReviewListRequestDTO(movieId, null, startIdx, length);
        repository.sendMovieReviewListRequest(dto, new CallBack<ReviewListDTO>() {
            @Override
            public void onResponse(ReviewListDTO response) {
                view.addReviews(response.getReviews());
                view.setLoadingIndicator(false);
            }

            @Override
            public void onFailure(VolleyError error) {
                view.setLoadingIndicator(false);
            }
        });

    }
}
