package com.epam.trainee.block13;

import java.util.concurrent.BlockingQueue;

public class BlockedQueuePullDecorator<T> {

    private BlockingQueue<T> queue;
    private volatile T pulledElement;
    private volatile boolean isAccepted;

    private final Object acceptMonitor = new Object();

    public BlockedQueuePullDecorator(BlockingQueue<T> queue) {
        this.queue = queue;
        isAccepted = false;
    }

    public T take() throws InterruptedException {
        if (isAccepted) {
            pulledElement = queue.take();
            isAccepted = false;
        }
        return pulledElement;
    }

    public void accept() {
        isAccepted = true;
    }

    public boolean hasMore() {
        synchronized (acceptMonitor) {
            return queue.size() > 0;
        }
    }
}
