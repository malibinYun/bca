package com.malibin.boostcourseace.movie.select;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.Movie;
import com.malibin.boostcourseace.movie.MovieHomeActivity;
import com.malibin.boostcourseace.movie.select.adpater.MoviePageFragmentStatePagerAdapter;
import com.malibin.boostcourseace.util.TempData;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */

public class MovieSelectFragment extends Fragment {

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

        movies = TempData.movie();
        initView();
    }

    private void initView() {
        inflatedView = getView();

        initMoviePagesView();
        setActivityAppbarTitle();
    }

    private void initMoviePagesView() {
        FragmentManager manager = getChildFragmentManager();
        // 여기서 getActivity().getSupportFragmentManager() 을 써서 Backstack 에서 돌아올때 뷰페이저가 없던것이다.
        // getChildFragmentManager() 으로 교체시 잘된다. 블로그 쓸 것.
        MoviePageFragmentStatePagerAdapter adapter =
                new MoviePageFragmentStatePagerAdapter(manager);
        adapter.addImageFragments(movies);

        ViewPager moviePager = inflatedView.findViewById(R.id.vp_movie_select_frag);
        moviePager.setAdapter(adapter);
    }

    private void setActivityAppbarTitle() {
        ((MovieHomeActivity) getActivity()).setAppbarTitle("영화 목록");
    }


}
