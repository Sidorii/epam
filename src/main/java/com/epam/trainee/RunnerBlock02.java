package com.epam.trainee;

import com.epam.trainee.controller.block02.GameController;
import com.epam.trainee.model.block02.GameLogic;
import com.epam.trainee.view.block01.ConsoleView;

import java.util.Scanner;

public class RunnerBlock02 {


    public static void main(String[] args) {
        int number;
        boolean isRightNumber = false;
        ConsoleView view = new ConsoleView(System.out);
        GameLogic logic = new GameLogic();

        GameController controller = new GameController(logic, view);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number: ");

        do {
            try {
                if (isRightNumber) {
                    processNewGame(sc, controller);
                    System.out.println("Enter number: ");
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

        System.out.print("Please, enter secret number:");
        if (sc.hasNextInt()) {
            number = sc.nextInt();
        } else {
            return;
        }
        controller.newGame(left, number, right);
    }
}
