package com.greghilston.rsicompanion;

import java.util.concurrent.TimeUnit;

/**
 * Acts as the presentation layer. Received calls from our view, forwards them to our model and makes
 * the call backs to the view.
 */
public class ExercisePresenter {
    final static long secondsInAMinute = 60;
    private ExerciseView exerciseView;
    private ExerciseModel exerciseModel;

    /**
     * @param v reference to the ExerciseView, used for callbacks
     */
    public ExercisePresenter(ExerciseView v) {
        this.exerciseView = v;
        this.exerciseModel = new ExerciseModel(this);

        this.exerciseView.updateStretch(this.exerciseModel.getCurrentExercise());
        this.setTimerText(exerciseModel.getCurrentExercise().getDurationMilliseconds());
    }

    /**
     * Calls back to ExerciseView to update graphically to next exercise
     */
    public void nextExercise() {
        exerciseView.updateStretch(exerciseModel.nextExercise());
        this.setTimerText(exerciseModel.getCurrentExercise().getDurationMilliseconds());
    }

    /**
     * Calls back to ExerciseView to update graphically to previous exercise
     */
    public void previousExercise() {
        exerciseView.updateStretch(exerciseModel.previousExercise());
        this.setTimerText(exerciseModel.getCurrentExercise().getDurationMilliseconds());
    }

    /**
     * Commands ExerciseModel to timer start or stop status
     */
    public void toggleStartStopTimer() {
        exerciseModel.toggleStartStopTimer();
    }

    /**
     * Sets the timer text
     *
     * @param timeMilliseconds time to format and set the timer text to
     */
    public void setTimerText(long timeMilliseconds) {
        String timeFormattedString;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeMilliseconds);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeMilliseconds) % secondsInAMinute;

        timeFormattedString = minutes + ":" + seconds;

        this.exerciseView.setTimerText(timeFormattedString);
    }

    /**
     * Updates the pause play button text
     * @param drawableId drawable id of the image to display
     */
    public void updatePausePlayButton(int drawableId) {
        this.exerciseView.updatePausePlayButtonImage(drawableId);
    }

    /**
     * Commands ExerciseModel to increment timer by defined amount
     */
    public void incrementTimer() {
        exerciseModel.incrementTimer();
    }

    /**
     * Commands ExerciseModel to restart timer to defined exercise's default amount
     */
    public void restartTimer() {
        exerciseModel.restartTimer();
    }
}
