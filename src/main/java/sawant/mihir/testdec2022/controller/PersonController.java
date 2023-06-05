package sawant.mihir.testdec2022.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sawant.mihir.testdec2022.domain.Person;
import sawant.mihir.testdec2022.service.PersonService;

import java.util.Map;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

   @PostMapping("/new")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        return ResponseEntity.ok(personService.add(person));
    }

    @GetMapping("find/{id}")
    public Person getPerson(@PathVariable long id){
       return personService.get(id);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePerson(@RequestParam  long id, @RequestParam  String name) {
        if(personService.update(id, name) != 0){
            return ResponseEntity.ok(getPerson(id));
        } else{
            return ResponseEntity.status(400).body("Something went wrong");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable long id){
        if(personService.delete(id) == 1){
            return ResponseEntity.status(HttpStatusCode.valueOf(200))
                    .body(Map.of("message:", "Person Deleted Successfully !"));
        } else{
            return ResponseEntity.of(ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(404), "Person not exists !")).build();
        }

    }


}
