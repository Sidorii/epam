package com.epam.trainee.model.block02;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class GameLogic {

    private static final String CONFIG_NAME = "application02.properties";
    private static final String PROP_NUMB = "number";
    private static final String PROP_LEFT = "bounds.left";
    private static final String PROP_RIGHT = "bounds.right";

    protected final int number;
    protected Bounds bounds;
    private LinkedList<Integer> history;

    public GameLogic() {
        try {
            Properties properties = new Properties();
            String fileName = ClassLoader.getSystemResource(CONFIG_NAME).getFile();
            properties.load(new FileInputStream(fileName));

            number = Integer.valueOf(properties.getProperty(PROP_NUMB));
            int left = Integer.valueOf(properties.getProperty(PROP_LEFT));
            int right = Integer.valueOf(properties.getProperty(PROP_RIGHT));
            bounds = new Bounds(left, right);
            history = new LinkedList<>();
        } catch (IOException exc) {
            exc.printStackTrace();
            throw new IllegalStateException("Can't find configuration from properties file: " + CONFIG_NAME);
        }
    }

    public GameLogic(int number) {
        this(number, new Bounds(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public GameLogic(int number, Bounds bounds) {

        if (bounds == null) {
            this.bounds = new Bounds(Integer.MIN_VALUE, Integer.MAX_VALUE);
        } else {
            this.bounds = bounds;
        }

        if (!this.bounds.hasNumber(number)) {
            throw new IllegalArgumentException("Number is out of bounds.");
        }
        this.history = new LinkedList<>();
        this.number = number;
    }


    public boolean guessNumber(int num) {
        history.add(num);

        if (!bounds.hasNumber(num)) {
            return false;
        }

        if (num == number) {
            return true;
        }

        if (num < number) {
            bounds = new Bounds(num, bounds.getRightBound());
        } else {
            bounds = new Bounds(bounds.getLeftBound(), num);
        }

        return false;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public LinkedList<Integer> getHistory() {
        return history;
    }

    public void resetStats() {
        history.removeAll(history);
    }
}
