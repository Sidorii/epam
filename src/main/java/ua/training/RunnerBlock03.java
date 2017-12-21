package ua.training;

import ua.training.mvcblocks.controller.block03.RecordsController;
import ua.training.mvcblocks.model.block03.bussiness.Notebook;
import ua.training.mvcblocks.view.block01.ConsoleView;

public class RunnerBlock03 {
    
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView(System.out);
        RecordsController controller = new RecordsController(consoleView, new Notebook());
        controller.addRecords();
    }
}
