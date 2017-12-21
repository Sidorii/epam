package ua.training.block13;

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
    public void process() throws InterruptedException {
        StudentInfo info = blockingQueue.take();
        if (info == endOfSequence) {
            blockingQueue.put(info);
            throw new InterruptedException();
        }
        processStudent(info);
    }

    private void processStudent(StudentInfo info) throws InterruptedException {
        if (info.getSpeciality() == speciality) {
            takeStudent(info);
            return;
        }
        boolean isRemoved = blockingQueue.remove(endOfSequence);
        blockingQueue.put(info);
        if (isRemoved) {
            blockingQueue.put(endOfSequence);
        }
    }
}
