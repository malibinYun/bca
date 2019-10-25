package com.malibin.boostcourseace.ui.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.ui.movie.select.MovieSelectFragment;

public class MovieHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MovieHomeActivityCall {

    private String currentFragment;
    private boolean isSortMenuOpen = false;

    private LinearLayout sortMenu;

    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isSortMenuOpen) {
                sortMenu.setVisibility(View.GONE);
                isSortMenuOpen = false;
                return;
            }
            sortMenu.setVisibility(View.VISIBLE);
            isSortMenuOpen = true;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };

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
    public void onPointerCaptureChanged(boolean hasCapture) {
    }


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
        initSortMenu();

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

    private void initSortMenu() {
        sortMenu = findViewById(R.id.menu_movie_home_sort_items);
        ConstraintLayout btnSortMenu = findViewById(R.id.btn_movie_home_act_sort);
        btnSortMenu.setOnClickListener(v -> {
            if (isSortMenuOpen) {
                closeSortMenu();
                return;
            }
            openSortMenu();
        });
        initSortMenuItems();
    }

    private void initSortMenuItems() {
        TextView tvSortMenu = findViewById(R.id.tv_movie_home_act_sort);
        ImageView btnReservationRate = findViewById(R.id.btn_movie_home_act_sort_reservation);
        ImageView btnCuration = findViewById(R.id.btn_movie_home_act_sort_curation);
        ImageView btnComingSoon = findViewById(R.id.btn_movie_home_act_sort_comingsoon);

        btnReservationRate.setOnClickListener(v -> {
            Toast.makeText(this, "예매율순 선택됨", Toast.LENGTH_SHORT).show();
            tvSortMenu.setText(R.string.reservation_order);
            closeSortMenu();
        });
        btnCuration.setOnClickListener(v -> {
            Toast.makeText(this, "큐레이션 선택됨", Toast.LENGTH_SHORT).show();
            tvSortMenu.setText(R.string.curation_order);
            closeSortMenu();
        });
        btnComingSoon.setOnClickListener(v -> {
            Toast.makeText(this, "상영예정 선택됨", Toast.LENGTH_SHORT).show();
            tvSortMenu.setText(R.string.coming_soon_order);
            closeSortMenu();
        });
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
        getSupportFragmentManager().popBackStack();
    }

    private void openSortMenu() {
        Animation downAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_down);
        downAnimation.setAnimationListener(animationListener);
        sortMenu.startAnimation(downAnimation);
    }

    private void closeSortMenu() {
        Animation upAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_up);
        upAnimation.setAnimationListener(animationListener);
        sortMenu.startAnimation(upAnimation);
    }

}
