package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method createStudent");
        Student saved = repository.save(student);
        logger.debug("Student saved: {}", saved);
        return saved;
    }

    public Student getStudent(Long id) {
        logger.info("Was invoked method getStudent, id={}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("There is no student with id={}", id);
                    return new EntityNotFoundException("Student not found");
                });
    }

    public List<Student> getAllStudents() {
        logger.info("Was invoked method getAllStudents");
        return repository.findAll();
    }

    public Student updateStudent(Long id, Student student) {
        logger.info("Was invoked method updateStudent, id={}", id);
        Student existing = getStudent(id);
        existing.setName(student.getName());
        existing.setAge(student.getAge());
        existing.setFaculty(student.getFaculty());
        Student updated = repository.save(existing);
        logger.debug("Updated student: {}", updated);
        return updated;
    }

    public void deleteStudent(Long id) {
        logger.warn("Deleting student with id={}", id);
        repository.deleteById(id);
    }
}

