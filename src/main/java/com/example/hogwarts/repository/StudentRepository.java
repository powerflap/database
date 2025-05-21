package com.example.hogwarts.repository;

import com.example.hogwarts.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
