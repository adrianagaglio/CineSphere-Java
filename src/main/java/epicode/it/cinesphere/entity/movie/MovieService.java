package epicode.it.cinesphere.entity.movie;

import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.actor.ActorRepo;
import epicode.it.cinesphere.entity.actor.GetActorRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepo movieRepo;
    private final ActorRepo actorRepo;
    private final Logger logger;

    public Movie save(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> save(List<Movie> movies) {
        return movieRepo.saveAll(movies);
    }

    public Movie findById(Long id) {
        return movieRepo.findById(id).orElse(null);
    }

    public List<Movie> findAll() {
        return movieRepo.findAll();
    }


    public int count() {
        return (int) movieRepo.count();
    }

    public void delete(Movie movie) {
        movieRepo.delete(movie);
    }

    public void delete(Long id) {
        movieRepo.deleteById(id);
    }

    public Movie findByTitle(String title) {
        return movieRepo.findFirstByTitleIgnoreCase(title);
    }

    public List<Movie> findByYear(int year) {
        return movieRepo.findByYearOrderByTitleAsc(year);
    }

    @Transactional
    public Movie newMovie(AddMovieRequest request) throws Exception {
        Movie newMovie = new Movie();
        Movie foundMovie = findByTitle(request.getTitle());
        if (foundMovie != null) throw new Exception("Movie already exists");
        newMovie.setTitle(request.getTitle());
        newMovie.setDescription(request.getDescription());
        newMovie.setYear(request.getYear());
        newMovie.setCoverImage(request.getCoverImage());
        newMovie.setDirector(request.getDirector());
        if (request.getCoverImage() != null && request.getCoverImage().size() > 0)
            newMovie.getCoverImage().addAll(request.getCoverImage());
        if (request.getGenres() != null && request.getGenres().size() > 0)
            newMovie.getGenres().addAll(request.getGenres());
        if (request.getActors() != null && request.getActors().size() > 0) {
            for (int i = 0; i < request.getActors().size(); i++) {
                GetActorRequest a = request.getActors().get(i);
                Actor managedActor = actorRepo.findFirstByNameAndSurname(a.getName(), a.getSurname());
                newMovie.getActors().add(managedActor);
                managedActor.getMovies().add(newMovie);
            }
        }
        return movieRepo.save(newMovie);
    }

    public Movie update(Movie request) throws Exception {
        Movie m = findById(request.getId());
        if (m == null) throw new Exception("Movie not found");
        if (request.getTitle() != null) m.setTitle(request.getTitle());
        if (request.getDescription() != null) m.setDescription(request.getDescription());
        if (request.getYear() == 0) m.setYear(request.getYear());
        if (request.getDirector() != null) m.setDirector(request.getDirector());
        if (request.getCoverImage() != null && request.getCoverImage().size() > 0) {
            m.getCoverImage().clear();
            m.getCoverImage().addAll(request.getCoverImage());
        }
        if (request.getGenres() != null && request.getGenres().size() > 0) {
            m.getGenres().clear();
            m.getGenres().addAll(request.getGenres());
        }
        if (request.getActors() != null && request.getActors().size() > 0) {
            m.getActors().clear();
            m.getActors().addAll(request.getActors());
        }
        return movieRepo.save(m);
    }
}
