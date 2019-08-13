package com.malibin.boostcourseace.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.select.MovieSelectFragment;

public class MovieHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_home);

        initView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.menu_movie_list) {

        } else if (id == R.id.menu_movie_api) {

        } else if (id == R.id.menu_movie_reserve) {

        } else if (id == R.id.menu_settings) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_movie_home_act);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_movie_home_act, fragment)
                //.addToBackStack(null)
                .commit();
    }

    private void initView() {
        initToolbar();

        initDrawableLayout();
        initNavigationView();

        initFirstFragment();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_movie_home_act);
        toolbar.setTitle("영화 목록");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    private void setTitle(String title) {
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(title);
    }

    private void initDrawableLayout() {
        Toolbar toolbar = findViewById(R.id.toolbar_movie_home_act);
        DrawerLayout drawer = findViewById(R.id.drawer_movie_home_act);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        NavigationView navigationView = findViewById(R.id.nav_movie_home_act);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFirstFragment() {
        Fragment movieSelectFragment = new MovieSelectFragment();
        replaceFragment(movieSelectFragment);
    }

}
