package com.greghilston.rsicompanion;

import android.os.CountDownTimer;

import java.util.Vector;

/**
 * Acts as the Model layer. Generally has the "business logic" and the state of our application
 */
public class ExerciseModel {
    private static final long oneSecondInMilliseconds = 1000;
    private static final long fiveSecondsInMilliseconds = 5000;
    private ExercisePresenter exercisePresenter;
    private Vector<Exercise> exercises;
    private int currentStretchIndex = 0;
    private boolean timerIsStarted = false;
    private long timeRemainingMilliseconds = 0;
    private CountDownTimer countDownTimer;

    /**
     * @param exercisePresenter reference to presenter
     */
    public ExerciseModel(ExercisePresenter exercisePresenter) {
        this.exercisePresenter = exercisePresenter;
        exercises = new Vector<>();
        exercises.add(new Exercise("Dog", R.drawable.dog, 20000));
        exercises.add(new Exercise("Cat", R.drawable.cat, 10000));
        currentStretchIndex = 0;
        timeRemainingMilliseconds = this.getCurrentExercise().getDurationMilliseconds();
    }

    /**
     * Selects a new exercise relative to the current
     * @param i spaces to move
     * @return new current exercise
     */
    private Exercise relativeSelectNewCurrentExercise(int i) {
        this.currentStretchIndex += i; // Handles both positive and negative i

        if (this.currentStretchIndex >= this.exercises.size()) { // Wrap back
            this.currentStretchIndex = 0;
        } else if (this.currentStretchIndex < 0) { // Wrap forward
            this.currentStretchIndex = this.exercises.size() - 1;
        }

        this.cancelTimer();
        return getCurrentExercise();
    }

    /**
     * Increments the current exercise by one, wrapping back if needed
     * @return new current exercise
     */
    public Exercise nextExercise() {
        final int nextExerciseIndex = 1;

        this.exercisePresenter.updatePausePlayButton(R.drawable.play);

        return relativeSelectNewCurrentExercise(nextExerciseIndex);
    }

    /**
     * Increments the current exercise by one, wrapping forward if needed
     * @return new current exercise
     */
    public Exercise previousExercise() {
        final int previousExerciseIndex = -1;

        this.exercisePresenter.updatePausePlayButton(R.drawable.play);

        return relativeSelectNewCurrentExercise(previousExerciseIndex);
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
        if (this.timerIsStarted) {
            long currentTimeRemainingMilliseconds = this.timeRemainingMilliseconds; // Used as a temp, as others edit this.timeRemainingMilliseconds
            this.cancelTimer();
            this.timeRemainingMilliseconds = currentTimeRemainingMilliseconds + ExerciseModel.fiveSecondsInMilliseconds;
            createAndStartTimer();
        } else {
            this.timeRemainingMilliseconds += ExerciseModel.fiveSecondsInMilliseconds;
        }

        this.exercisePresenter.setTimerText(this.timeRemainingMilliseconds);
    }

    /**
     * Restarts exercise timer to defined exercise's default amount
     */
    public void restartTimer() {
        this.timeRemainingMilliseconds = this.getCurrentExercise().getDurationMilliseconds();
        this.cancelTimer();
        this.exercisePresenter.updatePausePlayButton(R.drawable.play);
        this.exercisePresenter.setTimerText(this.timeRemainingMilliseconds);
    }

    /**
     * Cancels the timer if it exists, saving what time was left
     */
    private void cancelTimer() {
        if (this.countDownTimer != null) { // Case when switching exercises before starting timer
            this.countDownTimer.cancel();
        }
        this.timerIsStarted = false;
        this.timeRemainingMilliseconds = this.getCurrentExercise().getDurationMilliseconds();
    }

    /**
     * Toggles the exercise timer start or stop status
     */
    public void toggleStartStopTimer() {
        if (this.timerIsStarted) {
            cancelTimer();
            this.exercisePresenter.updatePausePlayButton(R.drawable.play);
        } else {
            createAndStartTimer();
            this.countDownTimer.start();
            this.timerIsStarted = true;
            this.exercisePresenter.updatePausePlayButton(R.drawable.pause);
        }
    }

    /**
     * @return current exercise
     */
    public Exercise getCurrentExercise() {
        return this.exercises.get(currentStretchIndex);
    }

    /**
     * @return time remaining in milli seconds
     */
    public long getTimeRemainingMilliseconds() {
        return timeRemainingMilliseconds;
    }
}
