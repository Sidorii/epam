package com.epam.trainee.controller.block03;

import com.epam.trainee.model.block03.*;
import com.epam.trainee.view.block01.View;

import java.util.ResourceBundle;
import java.util.Scanner;

public class RecordsController {

    private static final String BUNDLE_NAME = "RecordBookMessages";
    private ResourceBundle viewMessagesBundle = ResourceBundle.getBundle(BUNDLE_NAME);

    private RecordsBook recordsBook;
    private AttributeValidator attributeValidator;
    private View<String> view;

    private final Scanner scanner;

    public RecordsController(View<String> view) {
        this.view = view;
        this.scanner = new Scanner(System.in);
        recordsBook = new RecordsBook();
        attributeValidator = new RegexAttributeValidator();
    }


    public void addRecords() {

        RecordAttribute[] visibleAttributes = RecordAttribute.getAllVisible();
        RecordAttribute attributeKey;
        String attributeValue;

        do {
            recordsBook.createNewRecord();
            int index = 0;

            do {
                attributeKey = visibleAttributes[index++];
                attributeValue = getValidInput(attributeKey);
                recordsBook.appendToRecord(attributeKey, attributeValue);

            } while (index < RecordAttribute.visibleCount());

            recordsBook.appendRecordToBook();
            view.renderView("Fine! Now book is: " + recordsBook.getRecords() + '\n');
        } while (true);
    }


    private String getValidInput(RecordAttribute attributeKey) {

        String attributeValue;
        boolean isValid;

        do {
            attributeValue = readInputForAttribute(attributeKey);
            isValid = attributeValidator.validate(attributeKey, attributeValue);

            if (isValid) {
                break;
            }
            view.renderView("Entry doesn't match regex. Try again.\n");
        } while (true);

        return attributeValue;
    }

    private String readInputForAttribute(RecordAttribute currentAttribute) {
        String messageToView = viewMessagesBundle.getString(currentAttribute.name());
        view.renderView(messageToView);

        return scanner.nextLine();
    }
}
