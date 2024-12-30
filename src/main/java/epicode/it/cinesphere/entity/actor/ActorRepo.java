package epicode.it.cinesphere.entity.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActorRepo extends JpaRepository<Actor, Long> {

    @Query("SELECT a FROM Actor a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) AND LOWER(a.surname) LIKE LOWER(CONCAT('%', :surname, '%'))")
    public Actor findFirstByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}
