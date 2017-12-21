package ua.training.mvcblocks.model.block03.bussiness;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RecordAttribute {

    NAME(true),
    SURNAME(true),
    PATRONYMIC(true),
    FULL_NAME(false), //complex object
    NICKNAME(true),
    COMMENT(true),
    GROUP(true),
    PHONE(true),
    MOBILE_PHONE(true),
    MOBILE_PHONE2(true),
    EMAIL(true),
    SKYPE(true),
    FULL_ADDRESS(false), //complex object
    INDEX(true),
    CITY(true),
    STREET(true),
    HOUSE_NUMBER(true),
    FLAT_NUMBER(true),
    CREATION_DATE(false), //generates when Record became full
    LAST_MODIFYING(false);// generates when Record created or modified

    private boolean hasWriteAccess;

    RecordAttribute(boolean canUserType) {
        this.hasWriteAccess = canUserType;
    }

    public boolean canWrite() {
        return hasWriteAccess;
    }

    public static int getWriteAccessCount() {
        return (int) Arrays.stream(values())
                .filter(RecordAttribute::canWrite)
                .count();
    }

    public static RecordAttribute[] getAllWithWriteAccess() {
        return Arrays.stream(values())
                .filter(RecordAttribute::canWrite)
                .collect(Collectors.toList())
                .toArray(new RecordAttribute[getWriteAccessCount()]);
    }
}
