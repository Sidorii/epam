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

    private boolean isVisibleForUser;

    RecordAttribute(boolean isVisibleForUser) {
        this.isVisibleForUser = isVisibleForUser;
    }

    public boolean isVisibleForUser() {
        return isVisibleForUser;
    }

    public static int visibleCount() {
        return (int) Arrays.stream(values())
                .filter(RecordAttribute::isVisibleForUser)
                .count();
    }

    public static RecordAttribute[] getAllVisible() {
        return Arrays.stream(values())
                .filter(RecordAttribute::isVisibleForUser)
                .collect(Collectors.toList())
                .toArray(new RecordAttribute[visibleCount()]);
    }

}
