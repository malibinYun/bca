package com.malibin.boostcourseace.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.select.MovieSelectFragment;

public class MovieHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MovieHomeActivityCall {

    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_home);

        initView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_movie_home_act);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        boolean isLastStack = getSupportFragmentManager().getBackStackEntryCount() == 1;
        if (isLastStack) {
            finish();
            return;
        }

        super.onBackPressed();
        setCurrentFragmentOnBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = findViewById(R.id.drawer_movie_home_act);
        drawer.closeDrawer(GravityCompat.START);
        int id = menuItem.getItemId();

        if (id == R.id.menu_movie_list) {
            replaceMovieSelectFragment();
        }

        if (id == R.id.menu_movie_api) {

        }

        if (id == R.id.menu_movie_reserve) {

        }

        if (id == R.id.menu_settings) {

        }

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) { }


    @Override
    public void replaceFragment(Fragment fragment) {
        String fragmentName = fragment.getClass().getSimpleName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_movie_home_act, fragment)
                .addToBackStack(fragmentName)
                .commit();
        currentFragment = fragmentName;
    }

    public void setAppbarTitle(String title) {
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(title);
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
        MovieSelectFragment fragment = new MovieSelectFragment();
        fragment.setMovieHomeActivityCall(this);
        replaceFragment(fragment);
    }

    private void setCurrentFragmentOnBackPressed() {
        int index = getSupportFragmentManager().getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry topOfBackStack = getSupportFragmentManager().getBackStackEntryAt(index);
        currentFragment = topOfBackStack.getName();
    }

    private void replaceMovieSelectFragment() {
        boolean isSameFragment = currentFragment.equals(MovieSelectFragment.class.getSimpleName());
        if (isSameFragment) {
            return;
        }
        MovieSelectFragment fragment = new MovieSelectFragment();
        fragment.setMovieHomeActivityCall(this);
        replaceFragment(fragment);
    }

}
