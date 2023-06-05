package sawant.mihir.testdec2022.service;

import org.springframework.stereotype.Service;
import sawant.mihir.testdec2022.domain.Person;
import sawant.mihir.testdec2022.repository.PersonRepository;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person add(Person person){
        return personRepository.save(person);
    }

    public int delete(long id){
        if(personRepository.findById(id).isPresent()){
            personRepository.deleteById(id);
            return 1;
        } else{
            return -1;
        }
    }

    public Person get(long id){
        return personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person does not exist"));
    }


    public int update(long id, String name){
        return personRepository.updateById(id, name);
    }


}
