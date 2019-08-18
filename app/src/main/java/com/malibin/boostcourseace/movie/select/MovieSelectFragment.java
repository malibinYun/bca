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
import android.widget.ProgressBar;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.MovieHomeActivity;
import com.malibin.boostcourseace.movie.MovieHomeActivityCall;
import com.malibin.boostcourseace.movie.MovieShortInfo;
import com.malibin.boostcourseace.movie.select.adpater.MoviePageFragmentStatePagerAdapter;
import com.malibin.boostcourseace.network.MovieRepository;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */

public class MovieSelectFragment extends Fragment implements MovieSelectContract.View {

    private MovieSelectContract.Presenter presenter;
    private MovieHomeActivityCall activityCall;

    private List<MovieShortInfo> moviePages;
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
        initView();
        sendMovieListRequest();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter = null;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        ProgressBar progressBar = inflatedView.findViewById(R.id.progressBar_movie_select_frag);
        int visibility = active ? View.VISIBLE : View.INVISIBLE;
        progressBar.setVisibility(visibility);
    }

    @Override
    public void initMovieSelectPages(List<MovieShortInfo> response) {
        this.moviePages = response;
        initMovieSelectPagesView();
    }

    public void setMovieHomeActivityCall(MovieHomeActivityCall activityCall) {
        this.activityCall = activityCall;
    }

    private void initPresenter() {
        MovieRepository repository = MovieRepository.getInstance(getActivity());
        presenter = new MovieSelectPresenter(this, repository);
    }

    private void sendMovieListRequest() {
        presenter.sendMovieListRequest();
    }

    private void initView() {
        inflatedView = getView();
        setActivityAppbarTitle();
    }

    private void initMovieSelectPagesView() {
        FragmentManager manager = getChildFragmentManager();
        MoviePageFragmentStatePagerAdapter adapter =
                new MoviePageFragmentStatePagerAdapter(manager, activityCall);
        adapter.addImageFragments(moviePages);

        ViewPager moviePager = inflatedView.findViewById(R.id.vp_movie_select_frag);
        moviePager.setAdapter(adapter);
    }

    private void setActivityAppbarTitle() {
        ((MovieHomeActivity) getActivity()).setAppbarTitle("영화 목록");
    }

}
