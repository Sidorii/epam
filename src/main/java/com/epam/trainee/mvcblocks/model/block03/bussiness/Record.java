package com.epam.trainee.mvcblocks.model.block03.bussiness;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.epam.trainee.mvcblocks.model.block03.bussiness.RecordAttribute.*;

public class Record {

    private static final int RECORD_LENGTH = RecordAttribute.values().length;
    private static final int ATTRIBUTES_TO_WRITE_COUNT = RecordAttribute.getWriteAccessCount();

    private Map<RecordAttribute, String> recordAttributes;


    public Record() {
        recordAttributes = new HashMap<>(RECORD_LENGTH);
    }


    public void addAttribute(RecordAttribute attributeKey, String attributeValue) {

        if (!canAppend() || !recordAttributes.containsKey(attributeKey)) {
            recordAttributes.put(attributeKey, attributeValue);

            if (!canAppend()) {
                fillDateForRecord();
            }
        }
    }

    public String getAttributeValue(RecordAttribute key) {
        return recordAttributes.get(key);
    }

    public Map<RecordAttribute, String> getAttributes() {
        return Collections.unmodifiableMap(recordAttributes);
    }

    public boolean isRecordFull() {
        return recordAttributes.size() == RECORD_LENGTH;
    }

    public boolean canAppend() {
        return recordAttributes.size() < ATTRIBUTES_TO_WRITE_COUNT;
    }


    private void fillDateForRecord() {
        String fullName = buildFullName();
        String fullAddress = buildFullAddress();
        recordAttributes.put(FULL_ADDRESS, fullAddress);
        recordAttributes.put(FULL_NAME, fullName);
        recordAttributes.put(CREATION_DATE, new Date().toString());
        recordAttributes.put(LAST_MODIFYING, new Date().toString());
    }

    private String buildFullName() {
        return new StringBuilder(recordAttributes.get(SURNAME))
                .append(" ")
                .append(recordAttributes.get(NAME).charAt(0))
                .append('.').toString();
    }

    private String buildFullAddress() {
        return new StringBuilder(recordAttributes.get(INDEX)).append(" ")
                .append(recordAttributes.get(CITY)).append(" ")
                .append(recordAttributes.get(STREET)).append(" ")
                .append(recordAttributes.get(HOUSE_NUMBER)).append(" ")
                .append(recordAttributes.get(FLAT_NUMBER)).toString();
    }

    @Override
    public String toString() {
        StringBuilder objDescription = new StringBuilder();
        for (Map.Entry<RecordAttribute, String> attribute : recordAttributes.entrySet()) {
            objDescription.append(attribute.getKey())
                    .append(": ")
                    .append(attribute.getValue())
                    .append('\n');
        }
        return objDescription.toString();
    }
}
