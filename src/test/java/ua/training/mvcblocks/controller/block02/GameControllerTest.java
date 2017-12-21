package ua.training.mvcblocks.controller.block02;

import ua.training.mvcblocks.model.block02.GameLogic;
import ua.training.mvcblocks.view.block01.View;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class GameControllerTest {

    private GameController controller;
    private GameLogic gameLogic;
    private final int NUMBER = 10;
    private View<String> view;

    @Before
    public void setUp() {
        gameLogic = new GameLogic(NUMBER);
        view = createMock(View.class);
        controller = new GameController(gameLogic, view);
    }

    @Test
    public void testProceedInput() {

        view.renderView(anyObject());
        expectLastCall().anyTimes();

        replay(view);

        controller.inputNumber(NUMBER);
        controller.inputNumber(NUMBER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructController() {
        controller = new GameController(null, null);
    }

    @Test
    public void testNewGame() {
        controller.newGame(0, 10, 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewGameWrondWay() {
        controller.newGame(10, 5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewGameOutOfBounds() {
        controller.newGame(10, 5, 20);
    }
}