package com.greghilston.rsicompanion;

/**
 *
 */
public class Exercise {
    private String name;
    private int drawableId;
    private long durationMilliseconds;

    public Exercise(String name, int drawableId, int durationMilliseconds) {
        this.name = name;
        this.drawableId = drawableId;
        this.durationMilliseconds = durationMilliseconds;
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
    public long getDurationMilliseconds() {
        return durationMilliseconds;
    }
}
