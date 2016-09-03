package com.greghilston.rsicompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ExerciseView {
    private ExercisePresenter exercisePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exercisePresenter = new ExercisePresenter(this);
    }

    public void clickNextExercise(View view) {
        exercisePresenter.nextExercise();
    }

    public void clickPreviousExercise(View view) {
        exercisePresenter.previousExercise();
    }

    public void clickToggleStartStopTimer(View view) {
        exercisePresenter.toggleStartStopTimer();
    }

    public void clickIncrementTimer(View view) {
        exercisePresenter.incrementTimer();
    }

    public void clickRestartTimer(View view) {
        exercisePresenter.restartTimer();
    }

    @Override
    public void changeTimer(int timeSeconds) {
        // Update the timer
        TextView timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerTextView.setText(String.valueOf(timeSeconds));
    }

    @Override
    public void updateStretch(Exercise s) {
        // Update the exercise name
        TextView exerciseNameTextView = (TextView) findViewById(R.id.stretchNameTextview);
        exerciseNameTextView.setText(s.name);

        // Update the timer
        changeTimer(s.duration);

        // Update the exercise gif
        pl.droidsonroids.gif.GifTextView gifTextView = (pl.droidsonroids.gif.GifTextView) findViewById(R.id.currentExerciseGif);
        gifTextView.setBackgroundResource(s.drawableId);
        gifTextView.setFreezesAnimation(false);
    }
}
