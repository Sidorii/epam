package com.epam.trainee.block13;

import java.util.Random;

public class InstituteImpl extends Institute {

    private Random random;

    public InstituteImpl(String name, BlockedQueuePullDecorator<StudentInfo> blockedQueue) {
        super(name, blockedQueue);
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (blockingQueue.hasMore()){
                synchronized (blockingQueue) {
                    StudentInfo info = blockingQueue.take();
                    blockingQueue.accept();
                    takeStudent(info);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getRandomStudentsCount() {
        return random.nextInt(5) + 1;
    }
}
