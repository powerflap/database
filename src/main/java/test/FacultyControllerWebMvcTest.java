package test;

import com.example.hogwarts.controller.FacultyController;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.service.FacultyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateFaculty() throws Exception {
        Faculty faculty = new Faculty(1L, "Gryffindor", "Red", null);

        Mockito.when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Gryffindor")))
                .andExpect(jsonPath("$.color", is("Red")));
    }

    @Test
    public void shouldGetFacultyById() throws Exception {
        Faculty faculty = new Faculty(1L, "Slytherin", "Green", null);

        Mockito.when(facultyService.getFaculty(1L)).thenReturn(Optional.of(faculty));

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Slytherin")))
                .andExpect(jsonPath("$.color", is("Green")));
    }

    @Test
    public void shouldReturnNotFoundForMissingFaculty() throws Exception {
        Mockito.when(facultyService.getFaculty(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/faculty/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isOk());
    }
}
