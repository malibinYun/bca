package com.malibin.boostcourseace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class MovieDetailActivity extends AppCompatActivity {

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ratingBar = findViewById(R.id.rating_movie_detail_act_star_rating);
        ratingBar.setRating(8.2f);
    }
}