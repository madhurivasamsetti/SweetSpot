package com.example.vasam.sweetspot.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.vasam.sweetspot.R.id.video_space;

/**
 * Created by vasam on 7/27/2017.
 */

public class DetailFlowFragment extends Fragment {
//    @Nullable
//    @BindView(R.id.next_step)
//    ImageButton next_step;
//
//    @Nullable
//    @BindView(R.id.previous_step)
//    ImageButton previous_step;

    @Nullable
    @BindView(R.id.detail_instruction)
    TextView instruction;

//    @Nullable
//    @BindView(R.id.snackBar_position)
//    TextView snackBarView;

    private int position;
    private SimpleExoPlayer mExoPlayer;

    @BindView(video_space)
    SimpleExoPlayerView mPlayerView;

    @BindView(R.id.shutter_view)
    View shutterView;
    boolean isVisible;

    private ArrayList<RecipeSteps> stepsArrayList;

    private boolean isViewShown = false;

    public DetailFlowFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        stepsArrayList = getArguments().getParcelableArrayList(getString(R.string.steps_key));
        position = getArguments().getInt(getString(R.string.step_position_key));
        View rootView = inflater.inflate(R.layout.fragment_example_detail_flow, container, false);
        ButterKnife.bind(this, rootView);
        instruction.setText(stepsArrayList.get(position).getmDescription());
        playVideo();
        if (isVisible) {
            Log.d("DetailFlow.class", "visible in main");
            if (mExoPlayer != null) {
                mExoPlayer.setPlayWhenReady(true);
            }
        } else {
            if (mExoPlayer != null) {
                mExoPlayer.setPlayWhenReady(false);
            }
        }

        return rootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
    }

    public void playVideo() {
        String path = fetchVideoPath(position);
        if (path == null) {
            Toast.makeText(getContext(), "no video available", Toast.LENGTH_SHORT).show();
            mPlayerView.setVisibility(View.INVISIBLE);
            shutterView.setVisibility(View.VISIBLE);
        } else {
            initializeExoPlayer(Uri.parse(path));
        }
    }


    public String fetchVideoPath(int position) {
        String videoPath = null;
        if (stepsArrayList.get(position).getmVideoURL().isEmpty()) {
            if (stepsArrayList.get(position).getmThumbnailURL().isEmpty()) {
                //Toast.makeText(getContext(), "no video available in main list", Toast.LENGTH_SHORT).show();
            } else videoPath = stepsArrayList.get(position).getmThumbnailURL();
        } else videoPath = stepsArrayList.get(position).getmVideoURL();

        return videoPath;
    }

    public void initializeExoPlayer(Uri videoUri) {

        if (videoUri != null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
//            mExoPlayer.addListener(this);

            String userAgent = Util.getUserAgent(getContext(), "DetailFlowFragment");
            MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(getContext()
                    , userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
//                mExoPlayer.setPlayWhenReady(true);

        }


    }


    public void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }
}
