package com.epam.trainee.model.block03;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RecordAttribute {

    NAME(true),
    SURNAME(true),
    PATRONYMIC(true),
    NICKNAME(true),
    COMMENT(true),
    GROUP(true),
    PHONE(true),
    MOBILE_PHONE(true),
    MOBILE_PHONE2(true),
    EMAIL(true),
    SKYPE(true),
    ADDRESS(true), //complex object
    CREATION_DATE(false),
    LAST_MODIFYING(false);

    private boolean isShowForUser;

    RecordAttribute(boolean isShowForUser) {
        this.isShowForUser = isShowForUser;
    }

    public boolean isShowForUser() {
        return isShowForUser;
    }

    public static int size() {
        return (int) Arrays.stream(values())
                .filter(RecordAttribute::isShowForUser).count();
    }

    public static RecordAttribute[] getAllShown() {
        return Arrays.stream(values())
                .filter(RecordAttribute::isShowForUser).collect(Collectors.toList()).toArray(new RecordAttribute[size()]);
    }

}
