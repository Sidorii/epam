package com.epam.trainee;

import com.epam.trainee.controller.SentenceController;
import com.epam.trainee.model.Sentence;
import com.epam.trainee.view.View;

import java.util.Scanner;

public class Runner {


    public static void main(String[] args) {
        Sentence sentence = new Sentence();
        sentence.addWord(0, "Hello");
        sentence.addWord(1, "Word!");

        SentenceController controller = new SentenceController(sentence);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            System.out.print("Enter word: ");
            String word = scanner.next();
            View view = controller.proceedInput(word);
            view.show();
        }
    }
}
