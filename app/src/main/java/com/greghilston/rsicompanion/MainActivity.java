package com.greghilston.rsicompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Creates the MVP (Model View Presentation) structure and also holds the View component.
 */
public class MainActivity extends AppCompatActivity implements ExerciseView {
    private ExercisePresenter exercisePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exercisePresenter = new ExercisePresenter(this);
    }

    /**
     * Handles on next exercise button click listener
     *
     * @param view current view
     */
    public void clickNextExercise(View view) {
        exercisePresenter.nextExercise();
    }

    /**
     * Handles on previous exercise button click listener
     *
     * @param view current view
     */
    public void clickPreviousExercise(View view) {
        exercisePresenter.previousExercise();
    }

    /**
     * Handles on toggle start stop timer button click listener
     *
     * @param view current view
     */
    public void clickToggleStartStopTimer(View view) {
        exercisePresenter.toggleStartStopTimer();
    }

    /**
     * Handles on increment timer button click listener
     * @param view current view
     */
    public void clickIncrementTimer(View view) {
        exercisePresenter.incrementTimer();
    }

    /**
     * Handles on restart timer button click listener
     * @param view current view
     */
    public void clickRestartTimer(View view) {
        exercisePresenter.restartTimer();
    }

    @Override
    public void setTimer(int timeSeconds) {
        // Update the timer TextView
        TextView timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerTextView.setText(String.valueOf(timeSeconds));
    }

    @Override
    public void updateStretch(Exercise s) {
        // Update the exercise name
        TextView exerciseNameTextView = (TextView) findViewById(R.id.stretchNameTextview);
        exerciseNameTextView.setText(s.getName());

        // Update the timer
        setTimer(s.getDurationSeconds());

        // Update the exercise gif
        pl.droidsonroids.gif.GifTextView gifTextView = (pl.droidsonroids.gif.GifTextView) findViewById(R.id.currentExerciseGif);
        gifTextView.setBackgroundResource(s.getDrawableId());
        gifTextView.setFreezesAnimation(false);
    }

    @Override
    public void updatePausePlayButton(String s) {
        // Update the pause play button
        Button pausePlayButton = (Button) findViewById(R.id.pausePlayButton);
        pausePlayButton.setText(s);
    }
}
