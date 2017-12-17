package com.epam.trainee.mvcblocks.model.block03;

import com.epam.trainee.mvcblocks.model.block03.bussiness.RecordAttribute;

import java.util.List;

public class NotFullRecordException extends RuntimeException {

    private RecordAttribute[] emptyAttributes;

    public RecordAttribute[] getEmptyAttributes() {
        return emptyAttributes;
    }

    public NotFullRecordException(RecordAttribute... emptyAttributes) {
        this.emptyAttributes = emptyAttributes;
    }

    public NotFullRecordException(String message, RecordAttribute... emptyAttributes) {
        super(message);
        this.emptyAttributes = emptyAttributes;
    }

    public NotFullRecordException(String message, List<RecordAttribute> emptyAttributes) {
        super(message);
        this.emptyAttributes = emptyAttributes.toArray(new RecordAttribute[emptyAttributes.size()]);
    }
}
