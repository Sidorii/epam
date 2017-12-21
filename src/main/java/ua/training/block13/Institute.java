package ua.training.block13;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Institute extends Thread {

    private static AtomicInteger counter = new AtomicInteger(0);
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
                Thread.sleep(10);
                process();
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " is interrupted. That's mean the thread " +
                    "processed all appropriate students");
        }
    }

    protected abstract void process() throws InterruptedException;

    protected void takeStudent(StudentInfo info) {
            System.out.println("\t#" + counter.incrementAndGet());
            System.out.println("Institute '" + getName() + "' took: " + info);
            info.setName("accepted");
    }
}
