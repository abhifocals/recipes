package com.focals.recipes.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.media.session.MediaButtonReceiver;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.focals.recipes.R;
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
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends Fragment implements ExoPlayer.EventListener {

    @BindView(R.id.tv_stepDesc)
    TextView stepDescriptionTextView;

    @BindView(R.id.playerView)
    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    String stepDesc;
    Uri videoUri;

    public StepFragment() {
        // Required empty public constructor
    }

    public StepFragment(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        ButterKnife.bind(this, view);
        Uri sampleUri = Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffddf0_-intro-yellow-cake/-intro-yellow-cake.mp4");



        if (stepDesc == null) {
            stepDesc = getActivity().getIntent().getStringExtra("stepDesc");
            videoUri = Uri.parse(getActivity().getIntent().getStringExtra("videourl"));
        }

        stepDescriptionTextView.setText(stepDesc);

        initializePlayer(videoUri);

        return view;


    }

    private void initializePlayer(Uri sampleUri) {
        if (simpleExoPlayer == null) {

            // Create an instance of the ExoPlayer.
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), new DefaultTrackSelector(), new DefaultLoadControl());

            // Set the Player in the PlayerView
            playerView.setPlayer(simpleExoPlayer);

            // Set the Player EventListener
            simpleExoPlayer.addListener(this);

            // Prepare the MediaSource.
            MediaSource mediaSource = new ExtractorMediaSource(sampleUri, new DefaultDataSourceFactory(
                    getActivity(), "MyRecipes"), new DefaultExtractorsFactory(), null, null);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {

        } else if ((playbackState == ExoPlayer.STATE_READY)) {

        }

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
        simpleExoPlayer = null;
    }
}
