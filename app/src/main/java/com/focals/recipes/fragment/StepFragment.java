package com.focals.recipes.fragment;


import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.focals.recipes.R;
import com.focals.recipes.utils.Recipe;
import com.focals.recipes.utils.RecipeJsonParser;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepFragment extends Fragment implements ExoPlayer.EventListener, View.OnClickListener {

    @BindView(R.id.tv_stepDesc)
    TextView stepDescriptionTextView;

    @BindView(R.id.playerView)
    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    String stepDesc;
    Uri videoUri;

    @BindView(R.id.button_previousStep)
    Button previousButton;

    @BindView(R.id.button_nextStep)
    Button nextButton;

    ArrayList<Recipe> recipes;
    int currentStepPosition;
    int currentRecipePosition;
    MediaSource mediaSource;
    private Recipe currentRecipe;
    private ArrayList<HashMap<String, String>> currentRecipeSteps;
    private long playerCurrentPosition;

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

        if (stepDesc == null) {
            stepDesc = getActivity().getIntent().getStringExtra("stepDesc");
            videoUri = Uri.parse(getActivity().getIntent().getStringExtra("videourl"));
        }

        stepDescriptionTextView.setText(stepDesc);


        previousButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        recipes = RecipeJsonParser.getRecipes();
        currentStepPosition = getActivity().getIntent().getIntExtra("stepPosition", -1);
        currentRecipePosition = getActivity().getIntent().getIntExtra("recipePosition", -1);
        currentRecipe = recipes.get(currentRecipePosition);
        currentRecipeSteps = currentRecipe.getSteps();


        setButtonStatus();


        if (savedInstanceState != null && savedInstanceState.containsKey("PlayerCurrentPosition")) {
            playerCurrentPosition = savedInstanceState.getLong("PlayerCurrentPosition");
        }

        initializePlayer(videoUri, playerCurrentPosition);

        return view;

    }

    private void initializePlayer(Uri mediaUri, long currentPosition) {

        if (TextUtils.isEmpty(mediaUri.toString())) {

            showNoVideoToast(); //TODO: use return here  instead  of else below
        } else {
            if (simpleExoPlayer == null) {

                // Create an instance of the ExoPlayer.
                simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), new DefaultTrackSelector(), new DefaultLoadControl());

                // Set the Player in the PlayerView
                playerView.setPlayer(simpleExoPlayer);

                // Set the Player EventListener
                simpleExoPlayer.addListener(this);

                // Prepare the MediaSource.
                prepareMediaSource(mediaUri);


                if (currentPosition != 0) {
                    simpleExoPlayer.seekTo(currentPosition);
                }


                if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setFullScreen();
                }
            }
        }
    }

    private void prepareMediaSource(Uri mediaUri) {

        if (TextUtils.isEmpty(mediaUri.toString())) {
            showNoVideoToast();
        }
        mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                getActivity(), "MyRecipes"), new DefaultExtractorsFactory(), null, null);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true); //TODO: needed?

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong("PlayerCurrentPosition", simpleExoPlayer.getCurrentPosition());

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

        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onClick(View v) {

        int buttonId = v.getId();
        String newDesc;

        switch (buttonId) {

            case R.id.button_previousStep:

                if (currentStepPosition > 0) {
                    newDesc = currentRecipeSteps.get(currentStepPosition - 1).get(RecipeJsonParser.STEP_DESC);
                    stepDescriptionTextView.setText(newDesc);
                    videoUri = Uri.parse(currentRecipeSteps.get(currentStepPosition - 1).get(RecipeJsonParser.STEP_VIDEO));
                    prepareMediaSource(videoUri);
                    currentStepPosition = currentStepPosition - 1;
                }

                setButtonStatus();

                break;

            case R.id.button_nextStep:

                if (currentStepPosition < currentRecipeSteps.size() - 1) {
                    newDesc = currentRecipeSteps.get(currentStepPosition + 1).get(RecipeJsonParser.STEP_DESC);
                    stepDescriptionTextView.setText(newDesc);
                    videoUri = Uri.parse(currentRecipeSteps.get(currentStepPosition + 1).get(RecipeJsonParser.STEP_VIDEO));
                    prepareMediaSource(videoUri);
                    currentStepPosition = currentStepPosition + 1;
                }

                setButtonStatus();

                break;
        }
    }

    private void setButtonStatus() {

        // Previous Step Button

        if (currentStepPosition == 0) {
            previousButton.setEnabled(false);
        } else {
            previousButton.setEnabled(true);
        }

        // Next Step Button

        if (currentStepPosition == currentRecipeSteps.size() - 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
    }

    private void showNoVideoToast() {
        Toast.makeText(getActivity(), "No video is available for this step", Toast.LENGTH_LONG).show();
    }

    private void setFullScreen() {

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
        params.width = params.MATCH_PARENT;
        params.height = params.MATCH_PARENT;

        playerView.setLayoutParams(params);

        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
