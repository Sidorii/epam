package com.epam.trainee;

import com.epam.trainee.block13.*;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RunnerBlock13 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<StudentInfo> queue = new ArrayBlockingQueue<>(50, true);
        Set<StudentInfo> students = generateStudentSet(Speciality.BIOLOGY, 200);
        students.addAll(generateStudentSet(Speciality.MATH, 250));

        Producer producer = new Producer(students, queue);
        Institute otherInstitute = new InstituteImpl("LNU", queue, Producer.DONE);
        Institute otherInstitute1 = new SpecializedInstitute("KNU", queue, Speciality.BIOLOGY, Producer.DONE);
        Institute otherInstitute2 = new SpecializedInstitute("KPI", queue,Speciality.MATH, Producer.DONE);

        Thread prodTh = new Thread(producer, "producer");
        prodTh.start();
        otherInstitute.start();
        otherInstitute1.start();
        otherInstitute2.start();

        prodTh.join();
        otherInstitute.join();
        otherInstitute1.join();
        otherInstitute2.join();
        /*checks that all students infos had been processed by institutes (threads)*/
        students.stream().filter(s-> !s.getName().equals("accepted")).forEach(System.out::println);
    }

    private static Set<StudentInfo> generateStudentSet(Speciality speciality, int count) {
        Set<StudentInfo> resultSet = new HashSet<>();
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
