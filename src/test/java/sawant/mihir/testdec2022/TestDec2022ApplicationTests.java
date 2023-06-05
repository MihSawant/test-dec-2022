package sawant.mihir.testdec2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.ProblemDetail;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import sawant.mihir.testdec2022.domain.Person;



import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestDec2022ApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0-debian");


    private static long id;
    @Test
    void insertTest(){
        var person = new Person();
        person.setName("tom");

        var addedPerson = testRestTemplate.postForObject("/persons/new", person, Person.class);
        assertNotNull(addedPerson);
        id = addedPerson.getId();
        assertEquals(person.getName(), addedPerson.getName());

    }

    @Test
    void falseReadTest(){

        var error  = testRestTemplate.getForObject("/persons/find/{id}", ProblemDetail.class, 221);
        assertEquals(error.getDetail(), "Person does not exist");
    }

    @Test
    void updateCheckTest(){
        var nameToCheck = "Shyam";
        testRestTemplate.put("/persons/update?id={id}&name={name}",null, id, nameToCheck);

        Person updatedPerson = testRestTemplate.getForObject("/persons/find/{id}", Person.class, id);
        assertEquals(nameToCheck, updatedPerson.getName());
    }

    @Test
    void deleteTest(){
        testRestTemplate.delete("/persons/delete/{id}", id);
        var shyam = testRestTemplate.getForObject("/persons/find/{id}", ProblemDetail.class, id);
        assertEquals(shyam.getDetail(), "Person does not exist");

    }



}
