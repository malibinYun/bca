package com.malibin.boostcourseace.movie.select;

import com.malibin.boostcourseace.movie.MovieShortInfo;
import com.malibin.boostcourseace.network.response.MovieShortInfoResponseDTO;
import com.malibin.boostcourseace.util.BasePresenter;
import com.malibin.boostcourseace.util.BaseView;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */

public interface MovieSelectContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMovieSelectPages(List<MovieShortInfoResponseDTO> responseDTOs);

    }

    interface Presenter extends BasePresenter {

        void getMovieShortInfoList();

    }

}
