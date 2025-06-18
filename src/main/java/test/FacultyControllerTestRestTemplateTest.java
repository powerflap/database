package test;

import com.example.hogwarts.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/faculty";
    }

    @Test
    public void shouldCreateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Red");

        ResponseEntity<Faculty> response = restTemplate.postForEntity(getBaseUrl(), faculty, Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Gryffindor");
    }

    @Test
    public void shouldGetFacultyById() {
        Faculty faculty = new Faculty();
        faculty.setName("Ravenclaw");
        faculty.setColor("Blue");

        Faculty created = restTemplate.postForObject(getBaseUrl(), faculty, Faculty.class);

        ResponseEntity<Faculty> response = restTemplate.getForEntity(getBaseUrl() + "/" + created.getId(), Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Ravenclaw");
    }

    @Test
    public void shouldUpdateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Slytherin");
        faculty.setColor("Green");

        Faculty created = restTemplate.postForObject(getBaseUrl(), faculty, Faculty.class);
        created.setColor("Dark Green");

        HttpEntity<Faculty> requestUpdate = new HttpEntity<>(created);
        ResponseEntity<Faculty> response = restTemplate.exchange(getBaseUrl() + "/" + created.getId(), HttpMethod.PUT, requestUpdate, Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getColor()).isEqualTo("Dark Green");
    }

    @Test
    public void shouldDeleteFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Hufflepuff");
        faculty.setColor("Yellow");

        Faculty created = restTemplate.postForObject(getBaseUrl(), faculty, Faculty.class);

        restTemplate.delete(getBaseUrl() + "/" + created.getId());

        ResponseEntity<Faculty> response = restTemplate.getForEntity(getBaseUrl() + "/" + created.getId(), Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @org.testng.annotations.Test
    public void shouldSearchByNameOrColorIgnoreCase() {
        Faculty faculty = new Faculty();
        faculty.setName("Durmstrang");
        faculty.setColor("Grey");

        restTemplate.postForEntity(getBaseUrl(), faculty, Faculty.class);

        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(getBaseUrl() + "/search?query=durmstrang", Faculty[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }
}
