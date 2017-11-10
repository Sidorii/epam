package com.epam.trainee.model.block02;

public class Bounds {

    protected final int left;
    protected final int right;

    public Bounds(int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Left bound can't be bigger than right is");
        }
        this.left = left;
        this.right = right;
    }

    public int getLeftBound() {
        return left;
    }

    public int getRightBound() {
        return right;
    }

    public boolean hasNumber(int number) {
        return number >= left && number <= right;
    }
}
