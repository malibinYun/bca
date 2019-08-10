package com.malibin.boostcourseace.movie.select.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

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


    public void addImageFragment(String imageUrl) {
        MoviePageFragment fragment = MoviePageFragment.getInstance(imageUrl);
        imageFragments.add(fragment);
    }

    public void addImageFragment(int resourceId) {
        MoviePageFragment fragment = MoviePageFragment.getInstance(resourceId);
        imageFragments.add(fragment);
    }

    public void addImageFragment(Fragment fragment) {
        imageFragments.add(fragment);
    }

    public void addImageFragments(List<Integer> resourceIds) {
        for (int resourceId : resourceIds) {
            addImageFragment(resourceId);
        }
    }
}