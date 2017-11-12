package com.epam.trainee.model.block03;

import java.util.Arrays;
import java.util.List;

public class NotFullRecordException extends RuntimeException{

    private RecordAttribute[] emptyAttributes;

    public List<RecordAttribute> getEmptyAttributes() {
        return Arrays.asList(emptyAttributes);
    }

    public NotFullRecordException(RecordAttribute...emptyAttributes) {
        this.emptyAttributes = emptyAttributes;
    }

    public NotFullRecordException(String message, RecordAttribute...emptyAttributes) {
        super(message);
        this.emptyAttributes = emptyAttributes;
    }
}
