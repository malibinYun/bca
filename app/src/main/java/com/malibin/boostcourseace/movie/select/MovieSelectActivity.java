package com.malibin.boostcourseace.movie.select;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.select.adpater.MoviePageFragmentStatePagerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 10, 2019
 */

public class MovieSelectActivity extends AppCompatActivity {

    List<Integer> imageResourceIds = Arrays.asList(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_select);

        initView();
    }

    private void initView() {
        initMoviePagesView();
    }

    private void initMoviePagesView() {
//        FragmentManager manager = getSupportFragmentManager();
//        MoviePageFragmentStatePagerAdapter adapter =
//                new MoviePageFragmentStatePagerAdapter(manager);
//        adapter.addImageFragments(imageResourceIds);
//
//        ViewPager moviePager = findViewById(R.id.vp_movie_select_act);
//        moviePager.setAdapter(adapter);
    }


}
