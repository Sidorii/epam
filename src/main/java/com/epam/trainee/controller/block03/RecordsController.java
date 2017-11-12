package com.epam.trainee.controller.block03;

import com.epam.trainee.model.block03.FilterStrategy;
import com.epam.trainee.model.block03.RecordAttribute;
import com.epam.trainee.model.block03.RecordsBook;
import com.epam.trainee.view.block01.View;

import java.util.ResourceBundle;
import java.util.Scanner;

public class RecordsController {

    private static final String BUNDLE_NAME = "RecordBookMessages";
    private RecordsBook recordsBook;
    private View<String> outputView;
    private FilterStrategy filterStrategy = new FilterStrategy();
    private ResourceBundle viewMessagesBundle = ResourceBundle.getBundle(BUNDLE_NAME);

    public RecordsController(View<String> outputView) {
        recordsBook = new RecordsBook();
        this.outputView = outputView;
    }

    public void addRecord() {
        Scanner scanner = new Scanner(System.in);
        RecordAttribute[] recordAttributes;

        recordAttributes = RecordAttribute.getAllShown();

        do {
            recordsBook.createNewRecord();
            int attributeIndex = 0;

            do {
                RecordAttribute currentAttribute = recordAttributes[attributeIndex];

                outputView.renderView(viewMessagesBundle.getString(currentAttribute.name()));

                if (!scanner.hasNextLine()) {
                    break;
                }
                String input = scanner.nextLine();
                boolean isMatch = filterStrategy.doFilter(currentAttribute, input);
                if (!isMatch) {
                    outputView.renderView("Entry doesn't match regex. Try again");
                    continue;
                }
                attributeIndex++;
                recordsBook.appendToRecord(currentAttribute, input);
            } while (attributeIndex < recordAttributes.length);

            recordsBook.appendRecordToBook();
            outputView.renderView("Fine! Now book is: " + recordsBook.getRecords());
        } while (true);
    }
}
