package com.malibin.boostcourseace.movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.select.MovieSelectFragment;

public class MovieHomeActivity extends AppCompatActivity {

    private Fragment movieSelectFragment = new MovieSelectFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_home);

        initView();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_movie_home_act, fragment)
                .commit();
    }

    private void initView() {
        replaceFragment(movieSelectFragment);
    }

}
