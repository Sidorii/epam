package com.epam.trainee.controller;

import com.epam.trainee.model.Sentence;
import com.epam.trainee.view.View;

public class SentenceController {

    private Sentence sentence;
    private View<String> view;

    public SentenceController(Sentence sentence, View<String> view) {
        this.sentence = sentence;
        this.view = view;
    }

    public void proceedInput(String input) {
        throw new RuntimeException("implement me");
    }
}
