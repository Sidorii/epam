package com.epam.trainee.mvcblocks.model.block03;

import com.epam.trainee.mvcblocks.model.block03.bussiness.RecordAttribute;

public interface AttributeValidator{

    boolean validate(RecordAttribute attribute, String input);
}
