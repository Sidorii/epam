package com.epam.trainee.controller;

import com.epam.trainee.model.Sentence;
import com.epam.trainee.view.ConsoleView;
import com.epam.trainee.view.View;

import java.util.Iterator;

public class SentenceController {

    private Sentence pattern;
    private Iterator<String> wordIterator;

    public SentenceController(Sentence sentence) {
        pattern = sentence;
        this.wordIterator = pattern.getAllWords().iterator();
    }

    public View proceedInput(String word) {
        ConsoleView view;

        if (!wordIterator.hasNext()) {
            throw new IllegalStateException("You are in the end of the pattern thay is unreachible durring nirmal program execution");
        }

        String patternWord = wordIterator.next();
        if (patternWord.equals(word)) {
            if (wordIterator.hasNext()) {
                view = new ConsoleView(word, "is correct. Enter next word.");
            }else {
                view = new ConsoleView(word, "CONGRADS! YOU WIN!");
            }
        } else {
            view = new ConsoleView(word, " is not correct word. Should be " + patternWord);
            reset();
        }

        return view;
    }

    private void reset() {
        wordIterator = pattern.getAllWords().iterator();
    }
}
