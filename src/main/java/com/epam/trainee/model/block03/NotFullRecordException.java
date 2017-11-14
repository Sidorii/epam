package com.epam.trainee.model.block03;

import com.epam.trainee.model.block03.bussiness.RecordAttribute;

import java.util.Arrays;
import java.util.List;

public class NotFullRecordException extends RuntimeException{

    private List<RecordAttribute> emptyAttributes;

    public List<RecordAttribute> getEmptyAttributes() {
        return emptyAttributes;
    }

    public NotFullRecordException(RecordAttribute...emptyAttributes) {
        this.emptyAttributes = Arrays.asList(emptyAttributes);
    }

    public NotFullRecordException(String message, RecordAttribute...emptyAttributes) {
        super(message);
        this.emptyAttributes = Arrays.asList(emptyAttributes);
    }

    public NotFullRecordException(String message, List<RecordAttribute> emptyAttributes) {
        super(message);
        this.emptyAttributes = emptyAttributes;
    }
}
