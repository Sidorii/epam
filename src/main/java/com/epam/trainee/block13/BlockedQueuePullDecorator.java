package com.epam.trainee.block13;

import java.util.concurrent.BlockingQueue;

public class BlockedQueuePullDecorator<T> {

    private BlockingQueue<T> queue;
    private volatile T pulledElement;
    private volatile boolean isAccepted;

    public BlockedQueuePullDecorator(BlockingQueue<T> queue) {
        this.queue = queue;
        isAccepted = false;
    }

    public T take() throws InterruptedException {
        if (isAccepted) {
            pulledElement = queue.take();
        }
        return pulledElement;
    }

    public void accept() {
        isAccepted = true;
    }

    public boolean hasMore() {
        return queue.size() > 0;
    }
}
