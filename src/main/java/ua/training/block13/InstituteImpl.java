package ua.training.block13;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class InstituteImpl extends Institute {

    private Random random;

    public InstituteImpl(String name,
                         BlockingQueue<StudentInfo> blockedQueue,
                         StudentInfo endOfSequence) {
        super(name, blockedQueue, endOfSequence);
        random = new Random();
    }

    @Override
    public void process() throws InterruptedException {
        for (int i = 0; i < getRandomStudentsCount(); i++) {
            StudentInfo info = blockingQueue.take();
            if (info == endOfSequence) {
                blockingQueue.put(info);
                throw new InterruptedException();
            }
            takeStudent(info);
        }
    }

    private int getRandomStudentsCount() {
        return random.nextInt(5) + 1;
    }
}
