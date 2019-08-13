package com.malibin.boostcourseace.movie.select.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.malibin.boostcourseace.movie.Movie;
import com.malibin.boostcourseace.movie.select.MoviePageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 10, 2019
 */

public class MoviePageFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> imageFragments = new ArrayList<>();

    public MoviePageFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return imageFragments.get(position);
    }

    @Override
    public int getCount() {
        return imageFragments.size();
    }

    public void addImageFragments(List<Movie> movies) {
        for (Movie movie : movies) {
            addImageFragment(movie);
        }
    }

    private void addImageFragment(Movie movie) {
        MoviePageFragment fragment = MoviePageFragment.getInstance(movie);
        imageFragments.add(fragment);
    }

}