package com.epam.trainee.block13;

import java.util.concurrent.BlockingQueue;

public abstract class Institute extends Thread {

    private static volatile int counter = 0;
    protected StudentInfo endOfSequence;
    protected final BlockingQueue<StudentInfo> blockingQueue;

    protected Institute(String name,
                        BlockingQueue<StudentInfo> blockingQueue, StudentInfo endOfSequence) {
        super(name);
        this.endOfSequence = endOfSequence;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (process()) {
                    Thread.sleep(10);
                    continue;
                }
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract boolean process() throws InterruptedException;

    protected void takeStudent(StudentInfo info) {
        synchronized (Institute.class) {
            System.out.println("\t#" + ++counter);
            System.out.println("Institute '" + getName() + "' took: " + info);
            info.setRate(0.0f);
        }
    }
}
