package com.epam.trainee.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.trainee.utils.ValidationUtils.throwIfNullOrEmpty;
import static com.epam.trainee.utils.ValidationUtils.throwIfOutOfBounds;

public class Sentence {

    protected List<String> words;

    public Sentence() {
        words = new ArrayList<>();
    }

    public Sentence(String sentence, String regex) {
        throwIfNullOrEmpty(sentence, "Sentence can't be null");
        throwIfNullOrEmpty(regex, "Regex can't be null");

        String[] wordsArr = sentence.split(regex);
        words = prepareWords(Arrays.asList(wordsArr));
    }

    private List<String> prepareWords(List<String> dirtyWords) {
        dirtyWords.forEach(String::trim);

        return dirtyWords.stream()
                .filter((w) -> !w.equals(""))
                .collect(Collectors.toList());
    }

    public List<String> getWords() {
        return words;
    }

    public String getWord(int index) {
        throwIfOutOfBounds(0, words.size(), index, "words list");

        return words.get(index);
    }

    public void addWord(String word) {
        throwIfNullOrEmpty(word, "Attempt add string to sentence that does " +
                "not represent a word. String is: \'" + word + "\'");

        words.add(word);
    }

    public boolean removeWord(String word) {
        return word != null && words.remove(word);
    }

    public boolean removeWord(int index) {
        if (index < 0 || index >= words.size()) {
            return false;
        }
        String word = words.get(index);
        return removeWord(word);
    }
}
