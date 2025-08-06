package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for finding students with age between {} and {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<String> getNamesStartingWithA() {
        logger.info("Was invoked method for getting names starting with A");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name != null && name.toUpperCase().startsWith("А"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAverageAge() {
        logger.info("Was invoked method for getting average age of students");
        OptionalDouble average = studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average();
        return average.orElse(0);
    }
    public void printStudentsParallel() {
        List<Student> students = studentRepository.findAll();

        if (students.size() < 6) {
            logger.warn("Недостаточно студентов для демонстрации: найдено {}", students.size());
            return;
        }

        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        Thread thread1 = new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        });

        thread1.start();
        thread2.start();
    }
    private synchronized void printStudentName(String name) {
        System.out.println(name);
    }

    public void printStudentsSynchronized() {
        List<Student> students = studentRepository.findAll();

        if (students.size() < 6) {
            logger.warn("Недостаточно студентов для демонстрации: найдено {}", students.size());
            return;
        }

        printStudentName(students.get(0).getName());
        printStudentName(students.get(1).getName());

        Thread thread1 = new Thread(() -> {
            printStudentName(students.get(2).getName());
            printStudentName(students.get(3).getName());
        });

        Thread thread2 = new Thread(() -> {
            printStudentName(students.get(4).getName());
            printStudentName(students.get(5).getName());
        });

        thread1.start();
        thread2.start();
    }

}
