package epicode.it.cinesphere.entity.movie;

import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.rate.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    public Movie findFirstByTitleIgnoreCase(String title);

    public List<Movie> findByYearOrderByTitleAsc(int year);


}
