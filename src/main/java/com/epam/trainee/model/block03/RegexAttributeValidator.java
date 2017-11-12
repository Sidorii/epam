package com.epam.trainee.model.block03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexAttributeValidator implements AttributeValidator {

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean validate(RecordAttribute attribute, String input) {
        String regex = PropsRegexFactory.getRegex(attribute);
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
