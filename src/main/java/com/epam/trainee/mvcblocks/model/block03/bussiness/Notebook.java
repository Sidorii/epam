package com.epam.trainee.mvcblocks.model.block03.bussiness;

import com.epam.trainee.mvcblocks.model.block03.NotFullRecordException;
import com.epam.trainee.mvcblocks.utils.block01.ValidationUtils;

import java.util.*;

public class Notebook implements RecordContainer<Integer> {


    private Map<Integer, Record> records;
    private Integer currentRecordIndex;

    public Notebook() {
        records = new HashMap<>();
        currentRecordIndex = 0;
    }

    @Override
    public Integer createRecord(Record record) {
        ValidationUtils.throwIfNull(record,"Record");

        if (record.isRecordFull()) {
            records.put(currentRecordIndex, record);
            return currentRecordIndex++;
        } else {
            throw new NotFullRecordException("Can't append record to book" +
                    " because it's not a full", getUnfilledAttributes(record));
        }
    }

    @Override
    public void updateRecord(Integer id, Record record) {
        ValidationUtils.throwIfNull(record, "Record");

        if (id == null || !records.containsKey(id)) {
            createRecord(record);
        }else {
            records.replace(id, record);
        }
    }

    @Override
    public void deleteRecord(Integer id) {
        if (id == null || !records.containsKey(id)) {
            return;
        }
        records.remove(id);
    }

    @Override
    public Record getRecord(Integer id) {
        return records.get(id);
    }

    @Override
    public Map<Integer, Record> getRecords() {
        return records;
    }


    private RecordAttribute[] getUnfilledAttributes(Record record) {
        List<RecordAttribute> allAttributes = new ArrayList<>(Arrays.asList(RecordAttribute.values()));

        Set<RecordAttribute> filledAttributes = record.getAttributes().keySet();
        allAttributes.removeAll(filledAttributes);

        return allAttributes.toArray(new RecordAttribute[allAttributes.size()]);
    }
}
