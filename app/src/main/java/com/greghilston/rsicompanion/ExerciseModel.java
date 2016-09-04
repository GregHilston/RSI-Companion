package com.greghilston.rsicompanion;

import android.os.CountDownTimer;

import java.util.Vector;

/**
 * Acts as the Model layer. Generally has the "business logic" and the state of our application
 */
public class ExerciseModel {
    private static final long oneSecondInMilliseconds = 1000;
    private static final long fiveSecondsInMilliseconds = 5000;
    private static final long tenSecondsInMilliseconds = 10000;
    private ExercisePresenter exercisePresenter;
    private Vector<Exercise> exercises;
    private int currentStretchIndex = 0;
    private boolean timerIsStarted = false;
    private long timeRemainingMilliseconds = 0;
    private CountDownTimer countDownTimer;

    public ExerciseModel(ExercisePresenter exercisePresenter) {
        this.exercisePresenter = exercisePresenter;
        exercises = new Vector<>();
        exercises.add(new Exercise("Cat", R.drawable.cat, 10000));
        exercises.add(new Exercise("Dog", R.drawable.dog, 20000));
        currentStretchIndex = 0;
        timeRemainingMilliseconds = this.getCurrentExercise().getDurationMilliseconds();
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

        this.cancelTimer();
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

        this.cancelTimer();
        return getCurrentExercise();
    }

    /**
     * Creates a new CountDownTimer and starts it
     */
    private void createAndStartTimer() {
        countDownTimer = new CountDownTimer(getTimeRemainingMilliseconds(), ExerciseModel.oneSecondInMilliseconds) {
            public void onTick(long millisUntilFinished) {
                ExerciseModel.this.timeRemainingMilliseconds = millisUntilFinished;
                ExerciseModel.this.exercisePresenter.setTimerText(ExerciseModel.this.timeRemainingMilliseconds);
            }

            public void onFinish() {
                ExerciseModel.this.timeRemainingMilliseconds = 0;
                ExerciseModel.this.exercisePresenter.setTimerText(timeRemainingMilliseconds);
            }
        }.start();

        this.timerIsStarted = true;
    }

    /**
     * Increment exercise timer by defined amount
     */
    public void incrementTimer() {
        this.timeRemainingMilliseconds += ExerciseModel.fiveSecondsInMilliseconds;

        if (this.timerIsStarted) {
            this.cancelTimer();
            createAndStartTimer();
        }

        this.exercisePresenter.setTimerText(this.timeRemainingMilliseconds);
    }

    /**
     * Restarts exercise timer to defined exercise's default amount
     */
    public void restartTimer() {
        this.timeRemainingMilliseconds = this.getCurrentExercise().getDurationMilliseconds();
        this.cancelTimer();
        this.exercisePresenter.setTimerText(this.timeRemainingMilliseconds);
    }

    private void cancelTimer() {
        this.countDownTimer.cancel();
        this.timerIsStarted = false;
        this.timeRemainingMilliseconds = this.getCurrentExercise().getDurationMilliseconds();
    }

    /**
     * Toggles the exercise timer start or stop status
     */
    public void toggleStartStopTimer() {
        if (this.timerIsStarted) {
            cancelTimer();
        } else {
            createAndStartTimer();
            this.countDownTimer.start();
            this.timerIsStarted = true;
        }
    }

    public long getTimeRemainingMilliseconds() {
        return timeRemainingMilliseconds;
    }
}
