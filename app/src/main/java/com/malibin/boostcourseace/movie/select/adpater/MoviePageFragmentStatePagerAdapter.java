package com.malibin.boostcourseace.movie.select.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.malibin.boostcourseace.movie.Movie;
import com.malibin.boostcourseace.movie.MovieShortInfo;

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

    public void addImageFragments(List<MovieShortInfo> movieShortInfoList) {
        for (MovieShortInfo movieShortInfo : movieShortInfoList) {
            addImageFragment(movieShortInfo);
        }
    }

    private void addImageFragment(MovieShortInfo movieShortInfo) {
        MoviePageFragment fragment = MoviePageFragment.getInstance(movieShortInfo);
        imageFragments.add(fragment);
    }

}