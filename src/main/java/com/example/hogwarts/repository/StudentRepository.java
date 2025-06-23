package com.example.hogwarts.repository;

import com.example.hogwarts.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT COUNT(s) FROM Student s")
    int getStudentCount();

    @Query("SELECT AVG(s.age) FROM Student s")
    double getAverageAge();

    @Query("SELECT s FROM Student s ORDER BY s.id DESC LIMIT 5")
    List<Student> getLastFiveStudents(); // HQL — работает с сущностями
}
