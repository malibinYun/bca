package com.malibin.boostcourseace.ui.movie.detail;

import com.malibin.boostcourseace.ui.dto.ReviewListDTO;
import com.malibin.boostcourseace.ui.movie.Movie;
import com.malibin.boostcourseace.util.BasePresenter;
import com.malibin.boostcourseace.util.BaseView;

/**
 * Created By Yun Hyeok
 * on 8월 17, 2019
 */
public interface MovieDetailContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void initMovieDetailInfo(Movie movie);

        void initRecentReviews(ReviewListDTO dto);

    }

    interface Presenter extends BasePresenter {

        void sendMovieDetailRequest(int movieId);

        void sendRecentReviewRequest(int movieId);

        void sendLikeRequest(int movieId);

        void sendLikeCancelRequest(int movieId);

        void sendDisLikeRequest(int movieId);

        void sendDisLikeCancelRequest(int movieId);

    }
}
