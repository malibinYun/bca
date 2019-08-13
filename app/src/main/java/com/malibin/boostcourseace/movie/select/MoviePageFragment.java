package com.malibin.boostcourseace.movie.select;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.Movie;
import com.malibin.boostcourseace.movie.MovieHomeActivity;
import com.malibin.boostcourseace.movie.detail.MovieDetailFragment;

/**
 * Created By Yun Hyeok
 * on 8월 10, 2019
 */

public class MoviePageFragment extends Fragment {

    private View inflatedView;
    private Movie movie;

    public static MoviePageFragment getInstance(String imageUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", imageUrl);
        MoviePageFragment instance = new MoviePageFragment();
        instance.setArguments(bundle);
        return instance;
    }

    public static MoviePageFragment getInstance(int resourceId) {
        Bundle bundle = new Bundle();
        bundle.putInt("resourceId", resourceId);
        MoviePageFragment instance = new MoviePageFragment();
        instance.setArguments(bundle);
        return instance;
    }

    public static MoviePageFragment getInstance(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        MoviePageFragment instance = new MoviePageFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initValue();
        initView();
        loadImage();
    }

    private void initValue() {
        movie = getArguments().getParcelable("movie");
    }

    private void initView() {
        inflatedView = getView();
        initButton();
    }

    private void loadImage() {
        Activity activity = getActivity();

        ImageView imageView = inflatedView.findViewById(R.id.iv_movie_page_frag_image);

        if (getArguments() == null) {
            return;
        }

        String imageUrl = getArguments().getString("imageUrl");
        if (imageUrl != null) {
            Glide.with(activity).load(imageUrl).into(imageView);
            return;
        }
        Integer resourceId = getArguments().getInt("resourceId");
//        if (resourceId == null){
//            return;
//        }
        resourceId = movie.getImageUrl();
        Glide.with(activity).load(resourceId).into(imageView);


    }

    private void initButton() {
        Button btnGoDetail = inflatedView.findViewById(R.id.btn_movie_page_frag_detail);
        btnGoDetail.setOnClickListener(v ->
                replaceFragment2MovieDetail(movie)
        );
    }

    public void replaceFragment2MovieDetail(Movie movie) {
        if (getActivity() == null) {
            // 잘못된 호출임을 던지는 예외처리
            return;
        }
        MovieHomeActivity activity = (MovieHomeActivity) getActivity();
        MovieDetailFragment fragment = MovieDetailFragment.getInstance(movie);
        activity.replaceFragment(fragment);
    }
}
