package com.malibin.boostcourseace.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.ui.movie.MovieHomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), MovieHomeActivity.class);
            startActivity(intent);

            finish();
        }, 1000);
    }
}
