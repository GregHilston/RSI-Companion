package com.greghilston.rsicompanion;

import android.os.CountDownTimer;

import java.util.Vector;

/**
 * Acts as the Model layer. Generally has the "buisness logic" and the state of our application
 */
public class ExerciseModel {
    private ExercisePresenter exercisePresenter;
    private Vector<Exercise> exercises;
    private int currentStretchIndex = 0;
    private int timeLeftSeconds = 0;
    private boolean timerIsStarted = false;
    private CountDownTimer countDownTimer;

    public ExerciseModel(ExercisePresenter exercisePresenter) {
        this.exercisePresenter = exercisePresenter;
        exercises = new Vector<>();
        exercises.add(new Exercise("Cat", R.drawable.cat, 10));
        exercises.add(new Exercise("Dog", R.drawable.dog, 20));
        currentStretchIndex = 0;
        timeLeftSeconds = getCurrentExercise().getDurationSeconds();
    }

    /**
     * Gets current exercise
     *
     * @return current exercise
     */
    public Exercise getCurrentExercise() {
        return this.exercises.get(currentStretchIndex);
    }

    public int getTimeLeftSeconds() {
        return timeLeftSeconds;
    }

    private void setupNewTimer() {
        countDownTimer = new CountDownTimer(getTimeLeftSeconds() * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                System.out.println("millisUntilFinished " + millisUntilFinished);
                System.out.println("timeLeftSeconds " + getTimeLeftSeconds());
                timeLeftSeconds -= 1; // Reduce the time left by a second
                System.out.println("\ttimeLeftSeconds " + getTimeLeftSeconds());

                ExerciseModel.this.exercisePresenter.setTimer(getTimeLeftSeconds());
            }

            public void onFinish() {
                timeLeftSeconds = 0;
                ExerciseModel.this.exercisePresenter.setTimer(getTimeLeftSeconds());
            }
        }.start();
    }

    /**
     * Increments the current exercise by one, wrapping back if needed
     */
    public Exercise nextExercise() {
        this.currentStretchIndex++;

        if (this.currentStretchIndex >= this.exercises.size()) { // Wrap back
            this.currentStretchIndex = 0;
        }

        this.timeLeftSeconds = getCurrentExercise().getDurationSeconds();
        setupNewTimer();
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

        this.timeLeftSeconds = getCurrentExercise().getDurationSeconds();
        setupNewTimer();
        return getCurrentExercise();
    }

    /**
     * Toggles the timer start or stop status
     */
    public void toggleStartStopTimer() {
        if (this.timerIsStarted) {
            this.countDownTimer.cancel();
        } else {
            setupNewTimer();
            this.countDownTimer.start();
            this.timerIsStarted = true;
        }
    }

    /**
     * Increment timer by defined amount
     */
    public void incrementTimer() {
        this.timeLeftSeconds += 10;
        this.exercisePresenter.setTimer(this.timeLeftSeconds);
    }

    /**
     * Restarts timer to defined exercise's default amount
     */
    public void restartTimer() {
        this.timeLeftSeconds = getCurrentExercise().getDurationSeconds();
        this.exercisePresenter.setTimer(this.timeLeftSeconds);
    }
}
