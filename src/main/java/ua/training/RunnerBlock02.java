package ua.training;

import ua.training.mvcblocks.controller.block02.GameController;
import ua.training.mvcblocks.model.block02.Bounds;
import ua.training.mvcblocks.model.block02.GameLogic;
import ua.training.mvcblocks.view.block01.ConsoleView;

import java.util.Scanner;

public class RunnerBlock02 {


    public static void main(String[] args) {
        int number;
        boolean isRightNumber = false;
        ConsoleView view = new ConsoleView(System.out);
        GameLogic logic = new GameLogic(123, new Bounds(-10,200));

        GameController controller = new GameController(logic, view);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter secretNumber: ");

        do {
            try {
                if (isRightNumber) {
                    processNewGame(sc, controller);
                    System.out.println("Enter secretNumber: ");
                }

                if (!sc.hasNextInt()) {
                    System.out.println("Input is not a number");
                    break;
                }

                number = sc.nextInt();
                isRightNumber = controller.inputNumber(number);

            } catch (IllegalArgumentException exp) {
                exp.printStackTrace();
                isRightNumber = true;
            }
        } while (true);

    }

    private static void processNewGame(Scanner sc, GameController controller) {
        int left;
        int right;
        int number;

        System.out.print("Enter left bound: ");
        if (sc.hasNextInt()) {
            left = sc.nextInt();
        } else {
            return;
        }

        System.out.print("Enter right bound: ");
        if (sc.hasNextInt()) {
            right = sc.nextInt();
        } else {
            return;
        }

        System.out.print("Please, enter secret secretNumber:");
        if (sc.hasNextInt()) {
            number = sc.nextInt();
        } else {
            return;
        }
        controller.newGame(left, number, right);
    }
}
