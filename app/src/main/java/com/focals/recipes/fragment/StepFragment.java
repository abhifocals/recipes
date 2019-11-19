package com.focals.recipes.fragment;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

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


    StepChangeHandler stepChangeHandler;

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
        initializePlayer(videoUri);



        previousButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        recipes = RecipeJsonParser.getRecipes();
        currentStepPosition = getActivity().getIntent().getIntExtra("stepPosition", -1);
        currentRecipePosition = getActivity().getIntent().getIntExtra("recipePosition", -1);

        return view;

    }

    private void initializePlayer(Uri mediaUri) {
        if (simpleExoPlayer == null) {

            // Create an instance of the ExoPlayer.
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), new DefaultTrackSelector(), new DefaultLoadControl());

            // Set the Player in the PlayerView
            playerView.setPlayer(simpleExoPlayer);

            // Set the Player EventListener
            simpleExoPlayer.addListener(this);

            // Prepare the MediaSource.
            mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
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

    @Override
    public void onClick(View v) {

        int buttonId = v.getId();
        String newDesc;

        switch (buttonId) {

            case R.id.button_previousStep:


                if (currentStepPosition > 0)  {

                    currentRecipePosition = currentStepPosition -1;



                    newDesc = recipes.get(currentRecipePosition).getSteps().get(currentStepPosition-1).get(RecipeJsonParser.STEP_DESC);

                    stepDescriptionTextView.setText(newDesc);
                    
                    Uri uri = Uri.parse(recipes.get(currentRecipePosition).getSteps().get(currentStepPosition-1).get(RecipeJsonParser.STEP_VIDEO));

                    mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
                            getActivity(), "MyRecipes"), new DefaultExtractorsFactory(), null, null);
                    simpleExoPlayer.prepare(mediaSource);
                    simpleExoPlayer.setPlayWhenReady(true);


                }


                break;

            case R.id.button_nextStep:
                break;
        }
    }

    public interface StepChangeHandler{
        void onStepChange(int position);
    }
}
