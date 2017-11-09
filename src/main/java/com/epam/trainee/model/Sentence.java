package com.epam.trainee.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sentence {

    protected List<String> words;

    public Sentence() {
        words = new ArrayList<>();
    }

    public Sentence(String sentence, String regex) {
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
        return null;
    }

    public void addWord(String word) {
        throw new RuntimeException("implement me");
    }

    public boolean removeWord(String word) {
        throw new RuntimeException("inplement me");
    }

    public boolean removeWord(int index) {
        throw new RuntimeException("inplement me");
    }
}
