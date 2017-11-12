package com.epam.trainee.controller.block02;

import com.epam.trainee.model.block02.Bounds;
import com.epam.trainee.model.block02.GameLogic;
import com.epam.trainee.view.block01.View;

import java.util.ResourceBundle;

import static com.epam.trainee.model.block02.GameMessages.*;
import static com.epam.trainee.utils.block01.ValidationUtils.throwIfNull;

public class GameController {

    private static final String BUNDLE_NAME = "GameMessages";

    private GameLogic gameLogic;
    private View<String> view;
    private Bounds bounds;
    private ResourceBundle messageBundle;


    public GameController(GameLogic logic, View<String> view) {
        throwIfNull(logic, "Game logic");
        throwIfNull(view, "View");

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
                view.renderView(String.format(messageBundle.getString(STATS.name()),gameLogic.getInputHistory()));
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
