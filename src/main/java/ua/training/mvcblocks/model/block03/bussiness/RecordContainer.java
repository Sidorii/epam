package ua.training.mvcblocks.model.block03.bussiness;

import java.util.Map;

public interface RecordContainer<I> {

    I createRecord(Record record);

    void updateRecord(I id, Record record);

    void deleteRecord(I id);

    Record getRecord(I id);

    Map<Integer, Record> getRecords();
}
