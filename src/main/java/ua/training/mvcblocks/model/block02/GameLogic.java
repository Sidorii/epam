package ua.training.mvcblocks.model.block02;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class GameLogic {

    private static final String CONFIG_FILE_NAME = "application02.properties";
    private static final String PROP_NUMB = "number";
    private static final String PROP_LEFT = "bounds.left";
    private static final String PROP_RIGHT = "bounds.right";

    protected final int secretNumber;
    protected Bounds bounds;
    private LinkedList<Integer> inputHistory;

    public GameLogic() {
        try {
            Properties properties = new Properties();
            System.out.println(ClassLoader.getSystemResource(CONFIG_FILE_NAME));
            String fileName = ClassLoader.getSystemResource(CONFIG_FILE_NAME).getFile();
            properties.load(new FileInputStream(fileName));

            secretNumber = Integer.valueOf(properties.getProperty(PROP_NUMB));
            int left = Integer.valueOf(properties.getProperty(PROP_LEFT));
            int right = Integer.valueOf(properties.getProperty(PROP_RIGHT));
            bounds = new Bounds(left, right);
            inputHistory = new LinkedList<>();
        } catch (IOException exc) {
            exc.printStackTrace();
            throw new IllegalStateException("Can't find configuration from properties file: " + CONFIG_FILE_NAME);
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
        this.inputHistory = new LinkedList<>();
        this.secretNumber = number;
    }


    public boolean guessNumber(int number) {
        inputHistory.add(number);

        if (!bounds.hasNumber(number)) {
            return false;
        }

        if (number == secretNumber) {
            return true;
        }

        if (number < secretNumber) {
            bounds = new Bounds(number, bounds.getRightBound());
        } else {
            bounds = new Bounds(bounds.getLeftBound(), number);
        }

        return false;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public LinkedList<Integer> getInputHistory() {
        return inputHistory;
    }

    public void resetStats() {
        inputHistory.removeAll(inputHistory);
    }
}
