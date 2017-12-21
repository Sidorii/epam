package ua.training.mvcblocks.controller.block03;

import ua.training.mvcblocks.model.block03.AttributeValidator;
import ua.training.mvcblocks.model.block03.NotFullRecordException;
import ua.training.mvcblocks.model.block03.RegexAttributeValidator;
import ua.training.mvcblocks.model.block03.bussiness.Record;
import ua.training.mvcblocks.model.block03.bussiness.RecordAttribute;
import ua.training.mvcblocks.model.block03.bussiness.RecordContainer;
import ua.training.mvcblocks.view.block01.View;

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

        do {
            Record record = new Record();
            try {
                fillAttributesForRecord(record, visibleAttributes);
                recordContainer.createRecord(record);

            } catch (NotFullRecordException e) {
                view.renderView("Not full attributes was typed. Change or append next attributes:\n");

                fillAttributesForRecord(record,e.getEmptyAttributes());
                recordContainer.createRecord(record);
            }
            view.renderView("Fine! Now book is: " + recordContainer.getRecords() + '\n');
        } while (true);
    }

    private void fillAttributesForRecord(Record record, RecordAttribute[] attributes) {
        RecordAttribute attributeKey;
        String attributeValue;
        int index = 0;

        while (index < attributes.length){
            attributeKey = attributes[index++];

            if (!attributeKey.canWrite()) {
                continue;
            }
            attributeValue = getValidInput(attributeKey);
            record.addAttribute(attributeKey, attributeValue);
        }
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
