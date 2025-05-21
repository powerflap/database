package com.example.hogwarts.controller;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    // Create
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    // Read by id
    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    // Read all
    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    // Update
    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }
}
