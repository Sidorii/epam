package com.epam.trainee.model.block03;

import java.util.*;

public class RecordsBook {

    private static final int RECORD_LENGTH = RecordAttribute.visibleCount();

    private Map<Integer, Map<RecordAttribute, String>> records;
    private Map<RecordAttribute, String> currentRecord;
    private Integer currentRecordIndex;
    private boolean hasCurrentRecord;

    public RecordsBook() {
        records = new HashMap<>();
        currentRecordIndex = 0;
        hasCurrentRecord = false;
    }

    public void createNewRecord() {
        if (hasCurrentRecord) {
            throw new NotFullRecordException("Attempt create new record while " +
                    "current record is not full or is not persisted to book",
                    getUnfilledAttributes(currentRecord));
        }
        currentRecord = new HashMap<>(RECORD_LENGTH);
        hasCurrentRecord = true;
    }

    public void appendToRecord(RecordAttribute attribute, String value) {
        throwIfRecordNotFound();

        if (!isCurrentRecordFull() && !currentRecord.containsKey(attribute)) {
            currentRecord.put(attribute, value);
        }
    }

    public void appendRecordToBook() {
        throwIfRecordNotFound();

        if (isCurrentRecordFull()) {
            fillDateForRecord(currentRecord);
            records.put(currentRecordIndex++, currentRecord);
            cleanCurrentRecord();
        }else {
            throw new NotFullRecordException("Can't append current record to book" +
                    " because it's not a full", getUnfilledAttributes(currentRecord));
        }
    }

    public Map<Integer, Map<RecordAttribute, String>> getRecords() {
        return records;
    }

    private void fillDateForRecord(Map<RecordAttribute, String> record) {
        record.put(RecordAttribute.CREATION_DATE, new Date().toString());
        record.put(RecordAttribute.LAST_MODIFYING, new Date().toString());
    }

    private void cleanCurrentRecord() {
        hasCurrentRecord = false;
        currentRecord = null;
    }


    private RecordAttribute[] getUnfilledAttributes(Map<RecordAttribute, String> record) {
        List<RecordAttribute> allAttributes = Arrays.asList(RecordAttribute.values());

        Set<RecordAttribute> filledAttributes = record.keySet();
        allAttributes.removeAll(filledAttributes);

        return allAttributes.toArray(new RecordAttribute[allAttributes.size()]);
    }

    private void throwIfRecordNotFound() {
        if (!hasCurrentRecord) {
            throw new IllegalStateException("Attempt to get record that is not created yet." +
                    " Create new record before to access it");
        }
    }

    private boolean isCurrentRecordFull() {
        return currentRecord.size() == RECORD_LENGTH;
    }
}
