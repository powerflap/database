package com.example.hogwarts.service;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> findByNameOrColor(String value) {
        logger.info("Was invoked method for finding faculty by name or color: {}", value);
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(value, value);
    }

    public String getLongestFacultyName() {
        logger.info("Was invoked method for getting longest faculty name");
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .filter(name -> name != null)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
