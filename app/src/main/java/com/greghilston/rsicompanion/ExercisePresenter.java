package com.greghilston.rsicompanion;

public class ExercisePresenter {
    private ExerciseView exerciseView;
    private ExerciseModel exerciseModel;

    public ExercisePresenter(ExerciseView v) {
        this.exerciseView = v;
        this.exerciseModel = new ExerciseModel();

        this.exerciseView.updateStretch(this.exerciseModel.getCurrentExercise());
    }

    public void nextExercise() {
        exerciseView.updateStretch(exerciseModel.nextExercise());
    }

    public void previousExercise() {
        exerciseView.updateStretch(exerciseModel.previousExercise());
    }

    public void toggleStartStopTimer() {
        exerciseModel.startTimer();
    }

    public void stopTimer() {
        exerciseModel.stopTimer();
    }

    public void incrementTimer() {
        exerciseModel.incrementTimer();
    }

    public void restartTimer() {
        exerciseModel.restartTimer();
    }
}
