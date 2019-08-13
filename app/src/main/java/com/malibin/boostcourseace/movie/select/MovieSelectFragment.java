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
import com.malibin.boostcourseace.movie.select.adpater.MoviePageFragmentStatePagerAdapter;
import com.malibin.boostcourseace.util.TempData;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */

public class MovieSelectFragment extends Fragment {

    private List<Movie> movies = TempData.movie();
    private View inflatedView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_movie_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        inflatedView = getView();

        initMoviePagesView();
    }

    private void initMoviePagesView() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        MoviePageFragmentStatePagerAdapter adapter =
                new MoviePageFragmentStatePagerAdapter(manager);
        adapter.addImageFragments(movies);

        ViewPager moviePager = inflatedView.findViewById(R.id.vp_movie_select_act);
        moviePager.setAdapter(adapter);
    }


}
