package com.epam.trainee.block13.subproject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Runner {


    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(150);

        Consumer consumer1 = new Consumer(blockingQueue, "consumer1");
        Consumer consumer2 = new Consumer(blockingQueue, "consumer2");
        Consumer consumer3 = new Consumer(blockingQueue, "consumer3");

        Producer producer = new Producer(blockingQueue, "producer");

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}
