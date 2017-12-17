package com.epam.trainee.block13.subproject;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {


    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue, String name) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                queue.put(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
