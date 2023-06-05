package sawant.mihir.testdec2022.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sawant.mihir.testdec2022.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Transactional
    @Modifying
    @Query("update Person p set p.name = :name where p.id =:id")
    int updateById(long id, String name);

}
