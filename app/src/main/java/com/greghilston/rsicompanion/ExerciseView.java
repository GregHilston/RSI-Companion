package com.greghilston.rsicompanion;

public interface ExerciseView {
    /**
     * Sets the timer display
     *
     * @param timeSeconds seconds to set the timer to
     */
    void changeTimer(int timeSeconds);

    /**
     * Updates the graphically display based on a stretch
     *
     * @param s stretch to base the update on
     */
    void updateStretch(Exercise s);
}
