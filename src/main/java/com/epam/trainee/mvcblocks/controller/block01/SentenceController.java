package com.epam.trainee.mvcblocks.controller.block01;

import com.epam.trainee.mvcblocks.model.block01.Sentence;
import com.epam.trainee.mvcblocks.view.block01.View;

import java.util.Iterator;
import java.util.ResourceBundle;

import static com.epam.trainee.mvcblocks.model.block01.Messages.*;
import static com.epam.trainee.mvcblocks.utils.block01.ValidationUtils.throwIfNull;

public class SentenceController {

    protected ResourceBundle bundle;
    private Sentence sentence;
    private View<String> view;
    private Iterator<String> iterator;

    public SentenceController(Sentence sentence, View<String> view) {
        throwIfNull(sentence, "Input sentence");
        throwIfNull(view, "View");

        this.sentence = sentence;
        this.view = view;
        this.iterator = sentence.getWords().iterator();
        bundle = ResourceBundle.getBundle("Messages");
    }

    public void processInput(String input) {

        String result;
        String word;

        if (!iterator.hasNext()) {
            throw new IllegalStateException("No such words found for comparing");
        }

        word = iterator.next();
        result = String.format(bundle.getString(ENTER_TEXT.name()), input);

        if (isEquals(word, input)) {
            if (iterator.hasNext()) {
                result += bundle.getString(CORRECT.name());
            } else {
                result += bundle.getString(COMPLETED.name()) + sentence.getWords();
                resetInput();
            }
        } else {
            result += String.format(bundle.getString(INCORRECT.name()), word);
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
