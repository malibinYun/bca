package com.malibin.boostcourseace.movie.select;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    private Movie movie;
    private View inflatedView;
    private MovieHomeActivity activity;

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

    public void replaceFragment2MovieDetail(Movie movie) {
        MovieDetailFragment fragment = MovieDetailFragment.getInstance(movie);
        activity.replaceFragment(fragment);
    }

    private void initValue() {
        if (getArguments() == null) {
            return; // Throw error
        }
        if (getActivity() == null) {
            return; // Throw error
        }
        movie = getArguments().getParcelable("movie");
        activity = (MovieHomeActivity) getActivity();
    }

    private void initView() {
        inflatedView = getView();
        initButton();
        bindViewWithContents();
    }

    private void initButton() {
        Button btnGoDetail = inflatedView.findViewById(R.id.btn_movie_page_frag_detail);
        btnGoDetail.setOnClickListener(v -> replaceFragment2MovieDetail(movie));
    }

    private void bindViewWithContents() {
        TextView tvTitle = inflatedView.findViewById(R.id.tv_movie_page_frag_title);
        TextView tvReservationRate = inflatedView.findViewById(R.id.tv_movie_page_frag_reservation_rate);
        TextView tvMovieRate = inflatedView.findViewById(R.id.tv_movie_page_frag_rate);

        String reservationRate = movie.getReservationRate() + "%";
        tvTitle.setText(movie.getTitle());
        tvReservationRate.setText(reservationRate);
        tvMovieRate.setText(movie.getMovieRate().getText());
    }

    private void loadImage() {
        ImageView imageView = inflatedView.findViewById(R.id.iv_movie_page_frag_image);
        int resourceId = movie.getImageUrl();
        Glide.with(activity).load(resourceId).into(imageView);
    }


}
