package com.epam.trainee.block13.subproject;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {

    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue, String name) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (queue.size() > 0) {
                Integer oInt = queue.take();
                Thread.sleep(5);
                System.out.println(getName() + ": " + oInt);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
