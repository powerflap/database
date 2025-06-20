package com.example.hogwarts.controller;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/count")
    public int getCount() {
        return service.getStudentCount();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return service.getAverageAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFive() {
        return service.getLastFiveStudents();
    }
}
