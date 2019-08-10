package com.malibin.boostcourseace.movie.select;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.malibin.boostcourseace.R;

/**
 * Created By Yun Hyeok
 * on 8월 10, 2019
 */
public class MoviePageFragment extends Fragment {

    public static MoviePageFragment getInstance(String imageUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", imageUrl);
        MoviePageFragment instance = new MoviePageFragment();
        instance.setArguments(bundle);
        Log.d("Malibin Debug", " MoviePageFragment getInstance string 호출됨");
        return instance;
    }

    public static MoviePageFragment getInstance(int resourceId) {
        Bundle bundle = new Bundle();
        bundle.putInt("resourceId", resourceId);
        MoviePageFragment instance = new MoviePageFragment();
        instance.setArguments(bundle);
        Log.d("Malibin Debug", " MoviePageFragment getInstance int 호출됨 " + instance);
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

        loadImage();
    }

    private void loadImage() {
        Activity activity = getActivity();

        ImageView imageView = getView().findViewById(R.id.iv_movie_page_frag_image);

        if (getArguments() == null) {
            return;
        }

        String imageUrl = getArguments().getString("imageUrl");
        if (imageUrl != null) {
            Glide.with(activity).load(imageUrl).into(imageView);
            return;
        }
        Integer resourceId = getArguments().getInt("resourceId");
        if (resourceId == null) return;
        Glide.with(activity).load(resourceId).into(imageView);
    }
}
