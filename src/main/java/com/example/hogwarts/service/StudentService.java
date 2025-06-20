package com.example.hogwarts.service;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public int getStudentCount() {
        return repository.getStudentCount();
    }

    public double getAverageAge() {
        return repository.getAverageAge();
    }

    public List<Student> getLastFiveStudents() {
        return repository.getLastFiveStudents();
    }
}
