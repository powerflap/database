package test;

import com.example.hogwarts.model.Student;
import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTestRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/student";
    }

    @Test
    public void shouldCreateStudent() {
        Student student = new Student();
        student.setName("Harry");
        student.setAge(15);

        ResponseEntity<Student> response = restTemplate.postForEntity(getBaseUrl(), student, Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Harry");
    }

    @Test
    public void shouldGetStudentById() {
        Student student = new Student();
        student.setName("Hermione");
        student.setAge(16);

        Student created = restTemplate.postForObject(getBaseUrl(), student, Student.class);

        ResponseEntity<Student> response = restTemplate.getForEntity(getBaseUrl() + "/" + created.getId(), Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Hermione");
    }

    @Test
    public void shouldDeleteStudent() {
        Student student = new Student();
        student.setName("Ron");
        student.setAge(16);

        Student created = restTemplate.postForObject(getBaseUrl(), student, Student.class);

        restTemplate.delete(getBaseUrl() + "/" + created.getId());

        ResponseEntity<Student> response = restTemplate.getForEntity(getBaseUrl() + "/" + created.getId(), Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldUpdateStudent() {
        Student student = new Student();
        student.setName("Neville");
        student.setAge(17);

        Student created = restTemplate.postForObject(getBaseUrl(), student, Student.class);

        created.setAge(18);
        HttpEntity<Student> requestUpdate = new HttpEntity<>(created);
        ResponseEntity<Student> response = restTemplate.exchange(getBaseUrl() + "/" + created.getId(), HttpMethod.PUT, requestUpdate, Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getAge()).isEqualTo(18);
    }

    // Пример для запроса по возрасту (если реализован)
    @Test
    public void shouldFindStudentsByAgeBetween() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(getBaseUrl() + "/by-age?min=10&max=20", Student[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}
