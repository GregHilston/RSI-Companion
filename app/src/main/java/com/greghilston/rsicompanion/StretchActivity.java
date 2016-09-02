package com.greghilston.rsicompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.Vector;

public class StretchActivity extends AppCompatActivity {
    private Vector<Stretch> stretches;
    private int currentStretchIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stretches = new Vector<>();
        stretches.add(new Stretch("Cat", R.drawable.cat, 1));
        stretches.add(new Stretch("Dog", R.drawable.dog, 2));

        graphicallyUpdateCurrentStretch(currentStretchIndex);
    }

    /**
     * Updates the UI to reflect the currently selected stretch
     *
     * @param currentStretchIndex the stretch to display
     */
    private void graphicallyUpdateCurrentStretch(int currentStretchIndex) {
        Stretch currentStretch = this.stretches.get(currentStretchIndex);

        // Update the exercise name
        TextView exerciseNameTextView = (TextView) findViewById(R.id.stretchNameTextview);
        exerciseNameTextView.setText(currentStretch.name);

        // Update the timer
        TextView timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerTextView.setText(String.valueOf(currentStretch.duration));

        // Update the exercise gif
        pl.droidsonroids.gif.GifTextView gifTextView = (pl.droidsonroids.gif.GifTextView) findViewById(R.id.currentExerciseGif);
        gifTextView.setBackgroundResource(currentStretch.drawableId);
        gifTextView.setFreezesAnimation(false);
    }

    /**
     * Increments the current exercise by one, wrapping back if needed
     * @param view  passed by nextExercise button
     */
    public void nextExercise(View view) {
        this.currentStretchIndex++;

        if (this.currentStretchIndex >= this.stretches.size()) { // Wrap back
            this.currentStretchIndex = 0;
        }

        graphicallyUpdateCurrentStretch(this.currentStretchIndex);
    }

    /**
     * Increments the current exercise by one, wrapping forward if needed
     * @param view  passed by previousExercise button
     */
    public void previousExercise(View view) {
        this.currentStretchIndex--;

        if(this.currentStretchIndex < 0) { // Wrap forward
            this.currentStretchIndex = this.stretches.size() - 1;
        }

        graphicallyUpdateCurrentStretch(this.currentStretchIndex);
    }

    private class Stretch {
        private String name;
        private int drawableId;
        private int duration;

        public Stretch(String name, int drawableId, int duration) {
            this.name = name;
            this.drawableId = drawableId;
            this.duration = duration;
        }
    }
}
