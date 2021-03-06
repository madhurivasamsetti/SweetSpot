package com.example.vasam.sweetspot.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    @Nullable
    @BindView(R.id.detail_instruction)
    TextView instruction;

    @BindView(video_space)
    SimpleExoPlayerView mPlayerView;

    @BindView(R.id.shutter_view)
    ImageView shutterView;

    @BindView(R.id.video_shutter)
    RelativeLayout video_shutter;

    boolean isVisible;
    long currentPosition;
    private int position;

    private SimpleExoPlayer mExoPlayer;
    private ArrayList<RecipeSteps> stepsArrayList;


    public DetailFlowFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_detail_flow, container, false);
        ButterKnife.bind(this, rootView);

        stepsArrayList = getArguments().getParcelableArrayList(getString(R.string.steps_key));
        position = getArguments().getInt(getString(R.string.step_position_key));

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getLong(getString(R.string.current_exoplayer_position));
        }

        if (position != -1) {
            video_shutter.setVisibility(View.VISIBLE);
            if (instruction != null) {
                instruction.setVisibility(View.VISIBLE);
                instruction.setText(stepsArrayList.get(position).getmDescription());
            }
            playVideo();
        } else {
            video_shutter.setVisibility(View.INVISIBLE);
            if (instruction != null) {
                instruction.setVisibility(View.INVISIBLE);
            }
        }
        return rootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisible) {
            if (mExoPlayer != null) {
                mExoPlayer.seekTo(currentPosition);
                mExoPlayer.setPlayWhenReady(true);
            }
        } else {
            if (mExoPlayer != null) {
                mExoPlayer.setPlayWhenReady(false);
            }
        }
    }

    public void playVideo() {
        String path = fetchVideoPath(position);
        if (path == null) {
            mPlayerView.setVisibility(View.INVISIBLE);
        } else {
            initializeExoPlayer(Uri.parse(path));
        }
    }

    public String fetchVideoPath(int position) {
        String videoPath;
        if (stepsArrayList.get(position).getmVideoURL().isEmpty()) {
            if (stepsArrayList.get(position).getmThumbnailURL().isEmpty()) {
                Glide.with(this).load(R.drawable.no_video).into(shutterView);
                videoPath = null;
            } else {
                String thumbnailURL = stepsArrayList.get(position).getmThumbnailURL();
                String extension = thumbnailURL.substring(thumbnailURL.lastIndexOf("."));
                Log.d("DetailFlowFragment", "extension:" + extension);
                if (extension.equals(".mp4")) {
                    videoPath = thumbnailURL;
                } else {
                    Glide.with(this).load(thumbnailURL).into(shutterView);
                    videoPath = null;
                }
            }
        } else videoPath = stepsArrayList.get(position).getmVideoURL();
        return videoPath;
    }

    public void initializeExoPlayer(Uri videoUri) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getContext(), "DetailFlowFragment");
            MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(getContext()
                    , userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
        } else {
            mExoPlayer.seekTo(currentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        if (mExoPlayer != null) {
            currentState.putLong(getString(R.string.current_exoplayer_position),
                    mExoPlayer.getCurrentPosition());
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

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

}
