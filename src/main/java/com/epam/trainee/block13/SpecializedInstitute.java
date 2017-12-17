package com.epam.trainee.block13;

public class SpecializedInstitute extends Institute {

    private final Speciality speciality;

    public SpecializedInstitute(String name, BlockedQueuePullDecorator<StudentInfo> queue, Speciality speciality) {
        super(name, queue);
        this.speciality = speciality;
    }

    @Override
    public void popDocument() {
        StudentInfo info;
        try {
            synchronized (blockingQueue) {
                info = blockingQueue.take();

                if (info.getSpeciality().equals(speciality)) {
                    blockingQueue.accept();
                    takeStudent(info);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
