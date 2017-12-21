package ua.training.mvcblocks.controller.block02;

import ua.training.mvcblocks.model.block02.Bounds;
import ua.training.mvcblocks.model.block02.GameLogic;
import ua.training.mvcblocks.view.block01.View;
import ua.training.mvcblocks.utils.block01.ValidationUtils;

import java.util.ResourceBundle;

import static ua.training.mvcblocks.model.block02.GameMessages.*;

public class GameController {

    private static final String BUNDLE_NAME = "GameMessages";

    private GameLogic gameLogic;
    private View<String> view;
    private Bounds bounds;
    private ResourceBundle messageBundle;


    public GameController(GameLogic logic, View<String> view) {
        ValidationUtils.throwIfNull(logic, "Game logic");
        ValidationUtils.throwIfNull(view, "View");

        this.gameLogic = logic;
        this.view = view;
        this.bounds = gameLogic.getBounds();
        messageBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }




    public boolean inputNumber(int number) {
        if (!bounds.hasNumber(number)) {
            view.renderView(messageBundle.getString(OUT.name()));
        } else {

            if (gameLogic.guessNumber(number)) {
                view.renderView(String.format(messageBundle.getString(WIN.name()), number));
                view.renderView(String.format(messageBundle.getString(STATS.name()), gameLogic.getInputHistory()));
                gameLogic.resetStats();
                return true;
            } else {
                view.renderView(messageBundle.getString(FAIL.name()));
                view.renderView(String.format(messageBundle.getString(PREVIOUS.name()), gameLogic.getInputHistory().getLast()));
                bounds = gameLogic.getBounds();
            }
        }
        view.renderView(String.format(
                messageBundle.getString(REPEAT.name()), bounds.getLeftBound(), bounds.getRightBound()));
        return false;
    }


    public void newGame(int from, int number, int to) {
        bounds = new Bounds(from, to);
        if (!bounds.hasNumber(number)) {
            throw new IllegalArgumentException("Number is out of bounds");
        }
        gameLogic = new GameLogic(number, bounds);
    }
}
