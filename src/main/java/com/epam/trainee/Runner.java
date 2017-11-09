package com.epam.trainee;

import com.epam.trainee.controller.SentenceController;
import com.epam.trainee.model.Sentence;
import com.epam.trainee.view.ConsoleView;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        String input;

        Sentence sentence = new Sentence("Hello world!", " ");
        ConsoleView view = new ConsoleView(System.out);

        SentenceController controller = new SentenceController(sentence, view);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome! I have the simple sentence for you. Try to enter it with my help.");
        System.out.print("Enter word: ");

        do {
            if (!sc.hasNext()) {
                break;
            }

            input = sc.next();
            controller.processInput(input);
            System.out.print("\nEnter word: ");

        } while (!"quit".equals(input));
        System.out.println("Program is quit. Bye!");
    }
}
