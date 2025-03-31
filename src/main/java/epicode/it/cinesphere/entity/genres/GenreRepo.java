package epicode.it.cinesphere.entity.genres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepo extends JpaRepository<Genre, Long> {

    public Optional<Genre> findFirstByName(String name);
}
