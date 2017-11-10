package com.epam.trainee.model.block02;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoundsTest {

    private Bounds bounds;

    @Test
    public void createBounds() {
        bounds = new Bounds(0, 10);
        assertEquals(0, bounds.left);
        assertEquals(10, bounds.right);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithWrongInput() {
        bounds = new Bounds(10, 0);
    }

    @Test
    public void testGetters() {
        bounds = new Bounds(10, 20);
        assertEquals(10, bounds.getLeftBound());
        assertEquals(20, bounds.getRightBound());
    }

    @Test
    public void testHasNumber() {
        bounds = new Bounds(10, 20);
        for (int i = bounds.left; i <= bounds.right; i ++) {
            assertTrue(bounds.hasNumber(i));
        }
        assertFalse(bounds.hasNumber(9));
        assertFalse(bounds.hasNumber(21));
    }
}