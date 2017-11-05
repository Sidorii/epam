package com.epam.trainee.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Sentence {

    private static final String WORD_KEY = "sentence.word.";

    private List<String> words;

    public Sentence() {
        words = new ArrayList<>();
    }

    public Sentence(List<String> words) {
        this.words = words;
    }

    public Sentence(String propertiesFile) throws IOException {
        this();

        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesFile));

        long wordsCount = properties.keySet()
                .stream()
                .map(String::valueOf)
                .filter((w) -> (w).startsWith(WORD_KEY))
                .count();

        for (int i = 0; i < wordsCount; i++) {

            String keyName = WORD_KEY
                    .concat(String.valueOf(i));

            String word = properties.getProperty(keyName);

            if (word == null) {
                throw new RuntimeException("Wrong word enumeration. Word with index " + i + " does not exists");
            }
            words.add(word);
        }
    }

    public String getWord(int index) {
        return words.get(index);
    }

    public void addWord(int position, String word) {
        words.add(position, word);
    }

    public void removeWord(String word) {
        words.remove(word);
    }

    public void removeWord(int index) {
        words.remove(index);
    }

    public void updateWord(String oldWord, String newWord) {
        int index = words.indexOf(oldWord);
        updateWord(index, newWord);
    }

    public void updateWord(int index, String newWord) {
        words.set(index, newWord);
    }

    public Iterable<String> getAllWords() {
        return words;
    }
}
