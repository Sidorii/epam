package ua.training;

import ua.training.mvcblocks.controller.block01.SentenceController;
import ua.training.mvcblocks.model.block01.Sentence;
import ua.training.mvcblocks.view.block01.ConsoleView;

import java.util.Scanner;

public class RunnerBlock01 {

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
