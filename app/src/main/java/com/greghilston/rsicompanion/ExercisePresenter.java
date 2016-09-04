package com.greghilston.rsicompanion;

/**
 * Acts as the presentation layer. Received calls from our view, forwards them to our model and makes
 * the call backs to the view.
 */
public class ExercisePresenter {
    private ExerciseView exerciseView;
    private ExerciseModel exerciseModel;

    /**
     * Constructor
     *
     * @param v reference to the ExerciseView, used for callbacks
     */
    public ExercisePresenter(ExerciseView v) {
        this.exerciseView = v;
        this.exerciseModel = new ExerciseModel();

        this.exerciseView.updateStretch(this.exerciseModel.getCurrentExercise());
    }

    /**
     * Calls back to ExerciseView to update graphically to next exercise
     */
    public void nextExercise() {
        exerciseView.updateStretch(exerciseModel.nextExercise());
    }

    /**
     * Calls back to ExerciseView to update graphically to previous exercise
     */
    public void previousExercise() {
        exerciseView.updateStretch(exerciseModel.previousExercise());
    }

    /**
     * Commands ExerciseModel to timer start or stop status
     */
    public void toggleStartStopTimer() {

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
