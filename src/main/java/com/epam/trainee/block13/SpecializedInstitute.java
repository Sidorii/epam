package com.epam.trainee.block13;

import java.util.concurrent.BlockingQueue;

public class SpecializedInstitute extends Institute {

    private final Speciality speciality;

    public SpecializedInstitute(String name,
                                BlockingQueue<StudentInfo> queue,
                                Speciality speciality,
                                StudentInfo endOfSequence) {
        super(name, queue, endOfSequence);
        this.speciality = speciality;
    }


    @Override
    public boolean process() throws InterruptedException {
        StudentInfo info = blockingQueue.take();
        if (info == endOfSequence) {
            blockingQueue.put(info);
            return false;
        }
        processStudent(info);
        return true;
    }

    private void processStudent(StudentInfo info) throws InterruptedException {
        if (info.getSpeciality() == speciality) {
            takeStudent(info);
            return;
        }
        blockingQueue.put(info);
    }
}
