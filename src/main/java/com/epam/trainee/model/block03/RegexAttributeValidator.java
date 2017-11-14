package com.epam.trainee.model.block03;

import com.epam.trainee.model.block03.bussiness.Group;
import com.epam.trainee.model.block03.bussiness.RecordAttribute;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexAttributeValidator implements AttributeValidator {

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean validate(RecordAttribute attribute, String input) {

        if (attribute == RecordAttribute.GROUP) {
            return validateGroup(input);
        }

        pattern  = PropsPatternFactory.getPattern(attribute.name());
        matcher = pattern.matcher(input);

        return matcher.matches();
    }

    private boolean validateGroup(String input) {
        return Arrays.stream(Group.values())
                .anyMatch((g) -> g.name().equalsIgnoreCase(input));
    }
}
