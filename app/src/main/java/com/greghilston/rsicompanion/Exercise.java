package com.greghilston.rsicompanion;

/**
 *
 */
public class Exercise {
    private String name;
    private int drawableId;
    private int durationSeconds;

    public Exercise(String name, int drawableId, int durationSeconds) {
        this.name = name;
        this.drawableId = drawableId;
        this.durationSeconds = durationSeconds;
    }

    /**
     * Gets Exercise name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets drawable id
     *
     * @return drawable id
     */
    public int getDrawableId() {
        return drawableId;
    }

    /**
     * Gets duration in seconds
     *
     * @return duration in seconds
     */
    public int getDurationSeconds() {
        return durationSeconds;
    }
}
