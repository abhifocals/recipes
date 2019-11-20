package com.focals.recipes.fragment;


import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepFragment extends Fragment implements ExoPlayer.EventListener, View.OnClickListener {

    @BindView(R.id.tv_stepDesc)
    TextView stepDescriptionTextView;

    @BindView(R.id.playerView)
    PlayerView playerView;

    @BindView(R.id.button_previousStep)
    Button previousButton;

    @BindView(R.id.button_nextStep)
    Button nextButton;

    @BindView(R.id.exo_play)
    View playButton;


    private SimpleExoPlayer simpleExoPlayer;
    private Uri videoUri;
    MediaSource mediaSource;
    private long playerCurrentPosition;

    private ArrayList<Recipe> recipes;
    private int currentStepPosition;
    private int currentRecipePosition;
    private Recipe currentRecipe;
    private ArrayList<HashMap<String, String>> currentRecipeSteps;

    private boolean isTablet;

    public StepFragment() {
        // Required empty public constructor
    }

    /**
     * Used for creating StepFragment for Tablet (since StepActivity isn't started).
     *
     * @param recipePosition
     * @param stepPosition
     */
    public StepFragment(int recipePosition, int stepPosition) {
        this.currentRecipePosition = recipePosition;
        this.currentStepPosition = stepPosition;
        isTablet = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        // Binding view with Butterknife
        ButterKnife.bind(this, view);

        // Getting recipes from JsonParser
        recipes = RecipeJsonParser.getRecipes();

        // In case of Phone, getting current recipe and step position from StepActivity
        if (!isTablet) {
            currentRecipePosition = getActivity().getIntent().getIntExtra(getString(R.string.recipe_position), -1);
            currentStepPosition = getActivity().getIntent().getIntExtra(getString(R.string.step_position), -1);
        }

        // Using the current recipe and step position values
        currentRecipe = recipes.get(currentRecipePosition);
        currentRecipeSteps = currentRecipe.getSteps();

        // Setting the ingredient text and video url
        stepDescriptionTextView.setText(currentRecipeSteps.get(currentStepPosition).get(RecipeJsonParser.STEP_DESC));
        videoUri = Uri.parse(currentRecipeSteps.get(currentStepPosition).get(RecipeJsonParser.STEP_VIDEO));

        // Retreiving the current step and player position values saved during rotation
        if (savedInstanceState != null) {
            playerCurrentPosition = savedInstanceState.getLong(getString(R.string.player_position));
            currentStepPosition = savedInstanceState.getInt(getString(R.string.step_position));
        }

        // Initializing the player
        initializePlayer(videoUri, playerCurrentPosition);

        // Setting onClick listeners
        previousButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        playButton.setOnClickListener(this);

        // Enabling the Previous and Next Buttons depending on which step user is on
        setButtonStatus();

        return view;
    }

    /**
     * Initializes the ExoPlayer.
     *
     * @param mediaUri
     * @param currentPosition
     */

    private void initializePlayer(Uri mediaUri, long currentPosition) {

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

            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && !isTablet) {
                setFullScreen();
            }
        }
    }

    /**
     * Handles clicks of Previous button, Next button, and Play button (in case of no video url).
     *
     * @param v
     */

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


            case R.id.exo_play:
                if (currentRecipeSteps.get(currentStepPosition).get(RecipeJsonParser.STEP_VIDEO).isEmpty()) {
                    showNoVideoToast();
                }
        }
    }

    /**
     * Saves Current Step Position and Player Position for handling during rotation.
     *
     * @param outState
     */

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(getString(R.string.player_position), simpleExoPlayer.getCurrentPosition());
        outState.putInt(getString(R.string.step_position), currentStepPosition);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }


    ////////////  HELPERS ////////////

    /**
     * Helper used during Initializing the Player.
     *
     * @param mediaUri
     */

    private void prepareMediaSource(Uri mediaUri) {
        if (TextUtils.isEmpty(mediaUri.toString())) {
            showNoVideoToast();
        }
        mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                getActivity(), getString(R.string.app_name)), new DefaultExtractorsFactory(), null, null);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true); //TODO: needed?
    }

    /**
     * Releasing Media Player. Used during onDestroy.
     */

    private void releasePlayer() {

        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    /**
     * Setting to full-screen for Landscape Mode in Phone only.
     */

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

    /**
     * Shows a toast if video url is empty.
     */
    private void showNoVideoToast() {
        Toast toast = Toast.makeText(getActivity(), getString(R.string.noVideoAvailable), Toast.LENGTH_LONG);

        LinearLayout linearLayout = (LinearLayout) toast.getView();
        TextView textView = (TextView) linearLayout.getChildAt(0);
        textView.setTextSize(25);
        toast.show();
    }

    /**
     * Set Previous and Next Buttons to disabled depending on which step the user is on.
     */

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


    ////////////  ExoPlayer.EventListener Interface Methods ////////////

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
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

}
