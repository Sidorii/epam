package com.epam.trainee.block13;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<StudentInfo> queue;
    private Set<StudentInfo> students;

    public static final StudentInfo DONE = new StudentInfo();

    public Producer(Set<StudentInfo> students, BlockingQueue<StudentInfo> queue) {
        this.queue = queue;
        this.students = students;
    }

    @Override
    public void run() {
        Iterator<StudentInfo> stdIterator = students.iterator();
        try {
            while (stdIterator.hasNext()) {
                queue.put(stdIterator.next());
            }
            queue.put(DONE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
