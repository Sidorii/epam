package ua.training.mvcblocks.controller.block01;

import ua.training.mvcblocks.model.block01.Sentence;
import ua.training.mvcblocks.view.block01.View;
import ua.training.mvcblocks.model.block01.Messages;
import ua.training.mvcblocks.utils.block01.ValidationUtils;

import java.util.Iterator;
import java.util.ResourceBundle;

public class SentenceController {

    protected ResourceBundle bundle;
    private Sentence sentence;
    private View<String> view;
    private Iterator<String> iterator;

    public SentenceController(Sentence sentence, View<String> view) {
        ValidationUtils.throwIfNull(sentence, "Input sentence");
        ValidationUtils.throwIfNull(view, "View");

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
        result = String.format(bundle.getString(Messages.ENTER_TEXT.name()), input);

        if (isEquals(word, input)) {
            if (iterator.hasNext()) {
                result += bundle.getString(Messages.CORRECT.name());
            } else {
                result += bundle.getString(Messages.COMPLETED.name()) + sentence.getWords();
                resetInput();
            }
        } else {
            result += String.format(bundle.getString(Messages.INCORRECT.name()), word);
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
