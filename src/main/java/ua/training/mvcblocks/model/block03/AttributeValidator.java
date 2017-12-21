package ua.training.mvcblocks.model.block03;

import ua.training.mvcblocks.model.block03.bussiness.RecordAttribute;

public interface AttributeValidator{

    boolean validate(RecordAttribute attribute, String input);
}
