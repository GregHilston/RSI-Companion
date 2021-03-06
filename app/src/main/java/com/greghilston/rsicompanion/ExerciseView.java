package com.greghilston.rsicompanion;

/**
 * Provides a set of callbacks that a presenter can call to affect the visual display
 */
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
     * Updates the pause play button image
     *
     * @param drawableId drawable id of the image to display
     */
    void updatePausePlayButtonImage(int drawableId);
}
