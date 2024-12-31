package epicode.it.cinesphere.entity.rate;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepo extends JpaRepository<Rate, Long> {

    @Query("SELECT r.id AS id, r.vote AS vote, r.movie.id AS movieId, r.user.id AS userId " +
           "FROM Rate r WHERE r.user.id = :userId")
    List<IRateResponse> findByUserId(Long userId);

    @Query("SELECT r.id AS id, r.vote AS vote, r.movie.id AS movieId, r.user.id AS userId " +
           "FROM Rate r WHERE r.movie.id = :movieId")
    List<IRateResponse> findByMovieId(Long movieId);

    @Query("SELECT r.id AS id, r.vote AS vote, r.movie.id AS movieId, r.user.id AS userId " +
           "FROM Rate r WHERE r.id = :id")
    IRateResponse findIRateResponseBy(@Param("id") Long id);

    @Query("SELECT r.id AS id, r.vote AS vote, r.movie.id AS movieId, r.user.id AS userId " +
           "FROM Rate r")
    List<IRateResponse> findAllIRateResponseBy();
}
