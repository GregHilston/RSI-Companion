package com.greghilston.rsicompanion;

public interface ExerciseView {
    /**
     * Sets the timer display
     *
     * @param stringFormattedTime formatted string representing time left on timer
     */
    void setTimerText(String stringFormattedTime);

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
