package com.epam.trainee;

import com.epam.trainee.mvcblocks.controller.block03.RecordsController;
import com.epam.trainee.mvcblocks.model.block03.bussiness.Notebook;
import com.epam.trainee.mvcblocks.view.block01.ConsoleView;

public class RunnerBlock03 {
    
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView(System.out);
        RecordsController controller = new RecordsController(consoleView, new Notebook());
        controller.addRecords();
    }
}
