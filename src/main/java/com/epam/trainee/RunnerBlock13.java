package com.epam.trainee;

import com.epam.trainee.block13.*;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RunnerBlock13 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<StudentInfo> queue = new ArrayBlockingQueue<>(50, true);
        BlockedQueuePullDecorator<StudentInfo> queuePull = new BlockedQueuePullDecorator<>(queue);
        Set<StudentInfo> students = generateStudentSet(Speciality.BIOLOGY, 200);
        students.addAll(generateStudentSet(Speciality.MATH, 250));

        Producer producer = new Producer(students, queue);
//        SpecializedInstitute techInstitute = new SpecializedInstitute("KPI", queuePull, Speciality.MATH);
//        SpecializedInstitute artsInstitute = new SpecializedInstitute("KNU", queuePull, Speciality.BIOLOGY);
        InstituteImpl otherInstitute = new InstituteImpl("LNU", queuePull);
        InstituteImpl otherInstitute1 = new InstituteImpl("LNU1", queuePull);
        InstituteImpl otherInstitute2 = new InstituteImpl("LNU2", queuePull);

        Thread prodTh = new Thread(producer, "producer");
        prodTh.start();
//        techInstitute.start();
//        artsInstitute.start();
        otherInstitute.start();
        otherInstitute1.start();
        otherInstitute2.start();

        prodTh.join();
        System.out.println("prod");
        otherInstitute.join();
        System.out.println("other");
        otherInstitute1.join();
        System.out.println("other1");
        otherInstitute2.join();
        System.out.println("other2");
        System.out.println(students);
    }

    private static Set<StudentInfo> generateStudentSet(Speciality speciality, int count) {
        Set<StudentInfo> resultSet = new TreeSet<>();
        for (int i = 0; i < count; i++) {
            resultSet.add(generateRandomStudent(speciality));
        }
        return resultSet;
    }

    private static StudentInfo generateRandomStudent(Speciality speciality) {
        Random random = new Random();
        StudentInfo info = new StudentInfo();
        info.setName(String.valueOf(random.nextInt()));
        info.setRate(random.nextFloat());
        info.setSpeciality(speciality);
        return info;
    }
}
