package com.epam.trainee.controller.block03;

import com.epam.trainee.model.block03.AttributeValidator;
import com.epam.trainee.model.block03.NotFullRecordException;
import com.epam.trainee.model.block03.RegexAttributeValidator;
import com.epam.trainee.model.block03.bussiness.Record;
import com.epam.trainee.model.block03.bussiness.RecordAttribute;
import com.epam.trainee.model.block03.bussiness.RecordContainer;
import com.epam.trainee.view.block01.View;

import java.util.ResourceBundle;
import java.util.Scanner;

public class RecordsController {

    private static final String BUNDLE_NAME = "RecordBookMessages";
    private ResourceBundle viewMessagesBundle = ResourceBundle.getBundle(BUNDLE_NAME);

    private RecordContainer recordContainer;
    private AttributeValidator attributeValidator;
    private View<String> view;

    private final Scanner scanner;

    public RecordsController(View<String> view, RecordContainer recordContainer) {
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.recordContainer = recordContainer;
        attributeValidator = new RegexAttributeValidator();
    }


    public void addRecords() {

        RecordAttribute[] visibleAttributes = RecordAttribute.getAllWithWriteAccess();
        RecordAttribute attributeKey;
        String attributeValue;

        do {
            int index = 0;
            Record record = new Record();
            try {
                do {
                    attributeKey = visibleAttributes[index++];
                    attributeValue = getValidInput(attributeKey);
                    record.addAttribute(attributeKey, attributeValue);

                } while (index < RecordAttribute.getWriteAccessCount());
                recordContainer.createRecord(record);

            } catch (NotFullRecordException e) {
                view.renderView("Not full attributes was typed. Change or append next attributes:\n");
                for (RecordAttribute attribute : e.getEmptyAttributes()) {
                    if (attribute.canWrite()) {
                        String value = getValidInput(attribute);
                        record.addAttribute(attribute, value);
                    }
                }
                recordContainer.createRecord(record);
            }
            view.renderView("Fine! Now book is: " + recordContainer.getRecords() + '\n');
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
