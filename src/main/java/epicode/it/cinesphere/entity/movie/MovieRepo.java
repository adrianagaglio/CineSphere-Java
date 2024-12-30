package epicode.it.cinesphere.entity.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    public Movie findFirstByTitleIgnoreCase(String title);

    public List<Movie> findByYearOrderByTitleAsc(int year);


}
