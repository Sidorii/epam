package com.epam.trainee.model.block02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogicTest {

    private GameLogic logic;
    private final int NUMBER = 10;

    @Before
    public void setUp() {
        logic = new GameLogic(NUMBER);
    }

    @Test
    public void testNumberGuessing() {
        boolean isRight = logic.guessNumber(NUMBER);
        assertTrue(isRight);
    }

    @Test
    public void testWrongNumberGuessing() {
        int wrongNumber = 100;
        Bounds boundsBefore = logic.getBounds();
        boolean isRight = logic.guessNumber(100);
        Bounds boundsAfter = logic.getBounds();

        assertFalse(isRight);
        assertNotEquals(boundsBefore, boundsAfter);
        assertEquals(Integer.MIN_VALUE, boundsAfter.getLeftBound());
        assertEquals(100, boundsAfter.getRightBound());
    }

    @Test
    public void testWrongNumberGuessingAnotherCase() {
        int wrongNumber = 0;
        Bounds boundsBefore = logic.getBounds();
        boolean isRight = logic.guessNumber(0);
        Bounds boundsAfter = logic.getBounds();

        assertFalse(isRight);
        assertNotEquals(boundsBefore, boundsAfter);
        assertEquals(0, boundsAfter.getLeftBound());
        assertEquals(Integer.MAX_VALUE, boundsAfter.getRightBound());
    }

    @Test
    public void testInputOutOfBounds() {
        logic = new GameLogic(10, new Bounds(0, 15));
        Bounds boundsBefore = logic.getBounds();
        boolean isRight = logic.guessNumber(20);
        Bounds boundsAfter = logic.getBounds();

        assertFalse(isRight);
        assertEquals(boundsAfter, boundsBefore);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNumberOutOfBounds() {
        logic = new GameLogic(11, new Bounds(0, 5));
    }

    @Test
    public void testSetNullBounds() {
        logic = new GameLogic(NUMBER, null);
        boolean isRight = logic.guessNumber(NUMBER);
        assertTrue(isRight);
        assertNotNull(logic.getBounds());
    }
}