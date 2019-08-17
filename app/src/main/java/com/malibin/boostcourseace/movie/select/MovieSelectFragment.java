package com.malibin.boostcourseace.movie.select;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.Movie;
import com.malibin.boostcourseace.movie.MovieHomeActivity;
import com.malibin.boostcourseace.movie.MovieShortInfo;
import com.malibin.boostcourseace.movie.select.adpater.MoviePageFragmentStatePagerAdapter;
import com.malibin.boostcourseace.network.response.MovieShortInfoResponseDTO;
import com.malibin.boostcourseace.util.TempData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */

public class MovieSelectFragment extends Fragment implements MovieSelectContract.View {

    private MovieSelectContract.Presenter presenter;

    private List<MovieShortInfo> moviePages;
    private List<Movie> movies;
    private View inflatedView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initPresenter();
        initServer();


        //movies = TempData.movie();
        initView();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void setPresenter(MovieSelectContract.Presenter presenter) {

    }

    @Override
    public void showMovieSelectPages(List<MovieShortInfoResponseDTO> responseDTOs) {
        this.moviePages = convert(responseDTOs);
        initMoviePagesView();
    }

    void initPresenter() {
        presenter = new MovieSelectPresenter();
        ((MovieSelectPresenter) presenter).context = getActivity();
        ((MovieSelectPresenter) presenter).view = this;
    }

    void initServer() {
        presenter.start();
        presenter.getMovieShortInfoList();
    }

    private void initView() {
        inflatedView = getView();
        setActivityAppbarTitle();
    }

    private void initMoviePagesView() {
        FragmentManager manager = getChildFragmentManager();
        MoviePageFragmentStatePagerAdapter adapter =
                new MoviePageFragmentStatePagerAdapter(manager);
        adapter.addImageFragments(moviePages);

        ViewPager moviePager = inflatedView.findViewById(R.id.vp_movie_select_frag);
        moviePager.setAdapter(adapter);
    }

    private void setActivityAppbarTitle() {
        ((MovieHomeActivity) getActivity()).setAppbarTitle("영화 목록");
    }

    List<MovieShortInfo> convert(List<MovieShortInfoResponseDTO> list) {
        Log.d("Malibin Debug", "list : " + list);
        Log.d("Malibin Debug", "list : " + list.get(0).getClass().getSimpleName());

        return list.stream().map(MovieShortInfoResponseDTO::toMovieShortInfo)
                .collect(Collectors.toList());
    }

}
