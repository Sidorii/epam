package com.epam.trainee.model.block03;

public class FilterStrategy {

    public boolean doFilter(RecordAttribute attribute, String input) {
        return attribute.name().equals(input);
    }
}
