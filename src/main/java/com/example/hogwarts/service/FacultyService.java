package com.example.hogwarts.service;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty updateFaculty(Long id, Faculty updatedFaculty) {
        Optional<Faculty> existing = facultyRepository.findById(id);
        if (existing.isPresent()) {
            Faculty faculty = existing.get();
            faculty.setName(updatedFaculty.getName());
            faculty.setColor(updatedFaculty.getColor());
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}
