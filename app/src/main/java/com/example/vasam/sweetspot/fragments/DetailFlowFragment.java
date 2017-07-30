package com.example.vasam.sweetspot.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
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

public class DetailFlowFragment extends Fragment implements ExoPlayer.EventListener {
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

    private  int position;
    private SimpleExoPlayer mExoPlayer;

    @BindView(video_space)
    SimpleExoPlayerView mPlayerView;

    @BindView(R.id.shutter_view)
    View shutterView;
    boolean mTwoPane;
    boolean isPlaying = false;
    private ArrayList<RecipeSteps> stepsArrayList;
    long exoPosition;

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

        String path = fetchVideoPath(position);

        if (path == null) {
            Toast.makeText(getContext(), "no video available", Toast.LENGTH_SHORT).show();
            mPlayerView.setVisibility(View.INVISIBLE);
            shutterView.setVisibility(View.VISIBLE);
        } else {
            initializeExoPlayer(Uri.parse(path));
        }

//        if (instruction != null) {
//            instruction.setText(stepsArrayList.get(position).getmDescription());
//        }
//        final int sizeOfList = stepsArrayList.size();

//        if (next_step != null) {
//            next_step.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (position < sizeOfList - 1) {
//                        position++;
//                        previous_step.setColorFilter(Color.BLACK);
//                        next_step.setColorFilter(Color.BLACK);
//                        instruction.setText(stepsArrayList.get(position).getmDescription());
//                        String updatedPath = fetchVideoPath(position);
//                        if (updatedPath == null) {
//                            releasePlayer();
//                            Toast.makeText(getContext(), "no video available", Toast.LENGTH_SHORT).show();
//                            mPlayerView.setVisibility(View.INVISIBLE);
//                            shutterView.setVisibility(View.VISIBLE);
//                        } else {
//                            releasePlayer();
//                            mPlayerView.setVisibility(View.VISIBLE);
//                            shutterView.setVisibility(View.INVISIBLE);
//                            initializeExoPlayer(Uri.parse(updatedPath));
//                        }
//                    }
//                    if (position == sizeOfList - 1) {
//                        next_step.setColorFilter(Color.GRAY);
////                        Snackbar snackbar = Snackbar.make(snackBarView, "reached end of steps", Snackbar.LENGTH_LONG);
////                        snackbar.show();
////                    }
////                }
////
////            });
////        }
//        if (previous_step != null) {
//            previous_step.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (position > 0) {
//                        position--;
//                        previous_step.setColorFilter(Color.BLACK);
//                        next_step.setColorFilter(Color.BLACK);
//                        instruction.setText(stepsArrayList.get(position).getmDescription());
//                        String updatedPath = fetchVideoPath(position);
//                        if (updatedPath == null) {
//                            releasePlayer();
//                            Toast.makeText(getContext(), "no video available", Toast.LENGTH_SHORT).show();
//                            mPlayerView.setVisibility(View.INVISIBLE);
//                            shutterView.setVisibility(View.VISIBLE);
//                        } else {
//                            releasePlayer();
//                            mPlayerView.setVisibility(View.VISIBLE);
//                            shutterView.setVisibility(View.INVISIBLE);
//                            initializeExoPlayer(Uri.parse(updatedPath));
//                        }
//
//                    }
//                    if (position == 0) {
//                        previous_step.setColorFilter(Color.GRAY);
//                        Snackbar snackbar = Snackbar.make(snackBarView, "reached begining of steps", Snackbar.LENGTH_LONG);
//                        snackbar.show();
//                    }
//                }
//            });
//        }
        return rootView;
    }

//    @Override
//    public void onSaveInstanceState(Bundle currentState) {
//        currentState.putParcelableArrayList(getString(R.string.steps_key), stepsArrayList);
//        currentState.putInt(getString(R.string.step_position_key), position);
////        if(mExoPlayer!=null) {
////            mExoPlayer.getCurrentPosition();
////            currentState.putLong("exoposition", mExoPlayer.getCurrentPosition());
////        }
//    }

    public String fetchVideoPath(int position) {
        String videoPath = null;
        if (stepsArrayList.get(position).getmVideoURL().isEmpty()) {
            if (stepsArrayList.get(position).getmThumbnailURL().isEmpty()) {
                Toast.makeText(getContext(), "no video available in main list", Toast.LENGTH_SHORT).show();
            } else videoPath = stepsArrayList.get(position).getmThumbnailURL();
        } else videoPath = stepsArrayList.get(position).getmVideoURL();

        return videoPath;
    }

    public void initializeExoPlayer(Uri videoUri) {

        if (videoUri != null) {
            if (mExoPlayer == null) {
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
                mPlayerView.setPlayer(mExoPlayer);
                mExoPlayer.addListener(this);

                String userAgent = Util.getUserAgent(getContext(), "DetailFlowFragment");
                MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(getContext()
                        , userAgent), new DefaultExtractorsFactory(), null, null);
                mExoPlayer.prepare(mediaSource);
                mExoPlayer.setPlayWhenReady(true);
            }
        }


    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

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
