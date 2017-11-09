package com.epam.trainee.utils;

public final class ValidationUtils {

    public static void throwIfNull(Object o, String objName) {
        if (o == null) {
            throw new IllegalArgumentException(objName + " can't bu null");
        }
    }

    public static void throwIfNullOrEmpty(String string, String message) {
        if (string == null || string.equals("")) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void throwIfOutOfBounds(int min, int max, int index, String structureName) {
        if (index < min || index >= max) {
            throw new IndexOutOfBoundsException("Attempt to get element by index that is out of " +
                    structureName + " bounds. Index is: " + index);
        }
    }
}
