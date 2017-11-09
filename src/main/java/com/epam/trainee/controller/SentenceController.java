package com.epam.trainee.controller;

import com.epam.trainee.model.Sentence;
import com.epam.trainee.view.View;

import java.util.Iterator;

import static com.epam.trainee.utils.ValidationUtils.throwIfNull;

public class SentenceController {

    private Sentence sentence;
    private View<String> view;
    private Iterator<String> iterator;

    public SentenceController(Sentence sentence, View<String> view) {
        throwIfNull(sentence, "Input sentence");
        throwIfNull(view, "View");

        this.sentence = sentence;
        this.view = view;
        this.iterator = sentence.getWords().iterator();
    }

    public void processInput(String input) {

        String result;
        String word;

        if (!iterator.hasNext()) {
            throw new IllegalStateException("No such words found for comparing");
        }

        word = iterator.next();
        result = "You entered: \'" + input + "\'. ";

        if (isEquals(word, input)) {
            if (iterator.hasNext()) {
                result += "This is correct word. \nPlease, enter the next word.";
            } else {
                result += "Congratulations! You entered the whole sentence. Result is: " + sentence.getWords();
                resetInput();
            }
        } else {
            result += "Sorry, but it's not the correct word. Should have been: \'" + word + "\'. Try over again";
            resetInput();
        }

        view.renderView(result);
    }

    private void resetInput() {
        iterator = sentence.getWords().iterator();
    }

    private boolean isEquals(String currentWord, String input) {
        return input != null && !input.equals("") && currentWord.equals(input);
    }
}
