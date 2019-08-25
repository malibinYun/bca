package com.malibin.boostcourseace.ui.movie.select;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.db.DatabaseOpenHelper;
import com.malibin.boostcourseace.db.LocalRepository;
import com.malibin.boostcourseace.network.RemoteRepository;
import com.malibin.boostcourseace.ui.movie.MovieHomeActivity;
import com.malibin.boostcourseace.ui.movie.MovieHomeActivityCall;
import com.malibin.boostcourseace.ui.movie.MovieShortInfo;
import com.malibin.boostcourseace.ui.movie.select.adpater.MoviePageFragmentStatePagerAdapter;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */

public class MovieSelectFragment extends Fragment implements MovieSelectContract.View {

    private MovieSelectContract.Presenter presenter;
    private MovieHomeActivityCall activityCall;

    private List<MovieShortInfo> moviePages;
    private View inflatedView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initPresenter();
        initView();

        if (isInternetConnected()) {
            presenter.deleteLocalMovieList();
            presenter.requestRemoteMovieList();
            return;
        }
        presenter.requestLocalMovieList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter = null;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        ProgressBar progressBar = inflatedView.findViewById(R.id.progressBar_movie_select_frag);
        int visibility = active ? View.VISIBLE : View.INVISIBLE;
        progressBar.setVisibility(visibility);
    }

    @Override
    public void initMovieSelectPages(List<MovieShortInfo> response) {
        this.moviePages = response;
        initMovieSelectPagesView();
    }

    @Override
    public void showServerFailToast() {
        Toast.makeText(getContext(), R.string.server_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMissingMovieList() {
        Toast.makeText(getContext(), R.string.missing_movie_list, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDatabaseLoaded() {
        Toast.makeText(getContext(), R.string.database_loaded, Toast.LENGTH_SHORT).show();
    }

    public void setMovieHomeActivityCall(MovieHomeActivityCall activityCall) {
        this.activityCall = activityCall;
    }

    private void initPresenter() {
        DatabaseOpenHelper helper = DatabaseOpenHelper.getInstance(getContext(), "cinemaHeaven.db", null, 1);
        LocalRepository localRepository = LocalRepository.getInstance(helper);
        RemoteRepository remoteRepository = RemoteRepository.getInstance(getActivity());
        presenter = new MovieSelectPresenter(this, remoteRepository, localRepository);
    }

    private void initView() {
        inflatedView = getView();
        setActivityAppbarTitle();
    }

    private void initMovieSelectPagesView() {
        FragmentManager manager = getChildFragmentManager();
        MoviePageFragmentStatePagerAdapter adapter =
                new MoviePageFragmentStatePagerAdapter(manager, activityCall);
        adapter.addImageFragments(moviePages);

        ViewPager moviePager = inflatedView.findViewById(R.id.vp_movie_select_frag);
        moviePager.setAdapter(adapter);
    }

    private void setActivityAppbarTitle() {
        ((MovieHomeActivity) getActivity()).setAppbarTitle("영화 목록");
    }

    private boolean isInternetConnected() {
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null;
    }

}
