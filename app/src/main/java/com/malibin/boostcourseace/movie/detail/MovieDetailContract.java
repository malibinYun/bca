package com.malibin.boostcourseace.movie.detail;

import com.malibin.boostcourseace.movie.Movie;
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

    }

    interface Presenter extends BasePresenter {

        void sendMovieDetailRequest(int movieId);

    }
}
