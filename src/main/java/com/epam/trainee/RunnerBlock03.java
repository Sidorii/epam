package com.epam.trainee;

import com.epam.trainee.controller.block03.RecordsController;
import com.epam.trainee.view.block01.ConsoleView;

public class RunnerBlock03 {


    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView(System.out);
        RecordsController controller = new RecordsController(consoleView);
        controller.addRecords();
    }
}
