package com.epam.trainee.block13;

public abstract class Institute extends Thread{

    private static int counter = 0;

    protected final BlockedQueuePullDecorator<StudentInfo> blockingQueue;

    protected Institute(String name,
                        BlockedQueuePullDecorator<StudentInfo> blockingQueue) {
        super(name);
        this.blockingQueue = blockingQueue;
    }

    protected void takeStudent(StudentInfo info) {
        System.out.println("\t#" + ++counter);
        System.out.println("Institute '" + getName() + "' took: " + info);
    }
}
