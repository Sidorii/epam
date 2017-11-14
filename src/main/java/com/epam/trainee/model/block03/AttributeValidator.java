package com.epam.trainee.model.block03;

import com.epam.trainee.model.block03.bussiness.RecordAttribute;

public interface AttributeValidator{

    boolean validate(RecordAttribute attribute, String input);
}
