package com.epam.trainee.block13;

public class SpecializedInstitute extends Institute {

    private final Speciality speciality;

    public SpecializedInstitute(String name, BlockedQueuePullDecorator<StudentInfo> queue, Speciality speciality) {
        super(name, queue);
        this.speciality = speciality;
    }

    @Override
    public void run() {
        StudentInfo info;
        try {
            while (blockingQueue.hasMore()) {
                synchronized (blockingQueue) {
                    info = blockingQueue.take();

                    if (info.getSpeciality().equals(speciality)) {
                        blockingQueue.accept();
                        takeStudent(info);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
