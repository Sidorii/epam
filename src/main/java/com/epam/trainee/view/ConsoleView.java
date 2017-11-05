package com.epam.trainee.view;

public class ConsoleView implements View {

    private String word;
    private String message;


    public ConsoleView(String word, String message) {
        this.word = word;
        this.message = message;
    }

    @Override
    public void show() {
        System.out.printf("You enter: %s, %s\n", word, message);
    }
}
