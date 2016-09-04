package com.greghilston.rsicompanion;

public interface ExerciseView {
    /**
     * Sets the timer display
     *
     * @param timeSeconds seconds to set the timer to
     */
    void setTimer(int timeSeconds);

    /**
     * Updates the graphically display based on a stretch
     *
     * @param s stretch to base the update on
     */
    void updateStretch(Exercise s);

    /**
     * Updates the pause play button text
     *
     * @param s string to set button text to
     */
    void updatePausePlayButton(String s);
}
