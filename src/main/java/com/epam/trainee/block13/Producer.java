package com.epam.trainee.block13;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<StudentInfo> queue;
    private Set<StudentInfo> students;

    public Producer(Set<StudentInfo> students, BlockingQueue<StudentInfo> queue) {
        this.queue = queue;
        this.students = students;
    }

    @Override
    public void run() {
        Iterator<StudentInfo> stdIterator = students.iterator();
        while (stdIterator.hasNext()) {
            try {
                while (queue.size() < 50 && stdIterator.hasNext()) {
                    queue.put(stdIterator.next());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
