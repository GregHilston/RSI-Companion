package com.greghilston.rsicompanion;

import java.util.Vector;

/**
 *
 */
public class ExerciseModel {
    private Vector<Exercise> exercises;
    private int currentStretchIndex = 0;
    private int timeLeftSeconds = 0;

    public ExerciseModel() {
        exercises = new Vector<>();
        exercises.add(new Exercise("Cat", R.drawable.cat, 1));
        exercises.add(new Exercise("Dog", R.drawable.dog, 2));
        currentStretchIndex = 0;
    }

    /**
     * Gets current exercise
     *
     * @return current exercise
     */
    public Exercise getCurrentExercise() {
        return this.exercises.get(currentStretchIndex);
    }

    /**
     * Increments the current exercise by one, wrapping back if needed
     */
    public Exercise nextExercise() {
        this.currentStretchIndex++;

        if (this.currentStretchIndex >= this.exercises.size()) { // Wrap back
            this.currentStretchIndex = 0;
        }

        return getCurrentExercise();
    }

    /**
     * Increments the current exercise by one, wrapping forward if needed
     */
    public Exercise previousExercise() {
        this.currentStretchIndex--;

        if (this.currentStretchIndex < 0) { // Wrap forward
            this.currentStretchIndex = this.exercises.size() - 1;
        }

        return getCurrentExercise();
    }

    /**
     * Toggles the current start or stop state of the timer
     */
    public void toggleStartStopTimer() {

    }

    /**
     * Increment timer by defined amount
     */
    public void incrementTimer() {
        int incrementAmountSeconds = 10;

        this.timeLeftSeconds += incrementAmountSeconds;
    }

    /**
     * Restarts timer to defined exercise's default amount
     */
    public void restartTimer() {
        this.timeLeftSeconds = getCurrentExercise().duration;
    }
}
