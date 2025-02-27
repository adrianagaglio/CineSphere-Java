package epicode.it.cinesphere.entity.movie;

import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.actor.ActorService;
import epicode.it.cinesphere.entity.actor.dto.AddActorRequest;
import epicode.it.cinesphere.entity.actor.dto.GetActorRequest;
import epicode.it.cinesphere.entity.genres.Genre;
import epicode.it.cinesphere.entity.genres.GenreService;
import epicode.it.cinesphere.entity.movie.dto.AddMovieRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepo movieRepo;
    private final ActorService actorService;
    private final GenreService genreService;
    private final Logger logger;

    public Movie save(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> save(List<Movie> movies) {
        return movieRepo.saveAll(movies);
    }

    public Movie findById(Long id) {
        return movieRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public List<Movie> findAll() {
        return movieRepo.findAll();
    }


    public int count() {
        return (int) movieRepo.count();
    }

    public String delete(Movie movie) {
        movieRepo.delete(movie);
        return "Movie deleted successfully";
    }

    public String delete(Long id) {
        movieRepo.deleteById(id);
        return "Movie deleted successfully";
    }

    public Movie findByTitle(String title) {
        return movieRepo.findFirstByTitleIgnoreCase(title);
    }

    public List<Movie> findByYear(int year) {
        return movieRepo.findByYearOrderByTitleAsc(year);
    }


    @Transactional
    public Movie newMovie(AddMovieRequest request) {
        Movie newMovie = new Movie();
        Movie foundMovie = findByTitle(request.getTitle());
        if (foundMovie != null) throw new EntityExistsException("Movie already exists");
        save(newMovie);
        newMovie.setTitle(request.getTitle());
        newMovie.setDescription(request.getDescription());
        newMovie.setYear(request.getYear());
        newMovie.setCoverImage(request.getCoverImage());
        newMovie.setDirector(request.getDirector());
        if (request.getCoverImage() != null)
            newMovie.setCoverImage(request.getCoverImage());
        if (request.getGenres() != null && !request.getGenres().isEmpty()) {
            request.getGenres().forEach(genreName -> {
                Genre genre = genreService.addGenre(genreName);
                newMovie.getGenres().add(genre);
                // Associa il film al genere
                genre.getMovies().add(newMovie);
            });
        }
        if (request.getActors() != null && request.getActors().size() > 0) {
            for (int i = 0; i < request.getActors().size(); i++) {
                AddActorRequest a = request.getActors().get(i);
                Actor managedActor = actorService.findActorByNameAndSurname(a.getName(), a.getSurname());
                if (managedActor == null) managedActor = actorService.saveActor(a);
                newMovie.getActors().add(managedActor);
                managedActor.getMovies().add(newMovie);
            }
        }
        return movieRepo.save(newMovie);
    }

    public Movie update(Movie request) {
        Movie m = findById(request.getId());
        if (request.getTitle() != null) m.setTitle(request.getTitle());
        if (request.getDescription() != null) m.setDescription(request.getDescription());
        if (request.getYear() == 0) m.setYear(request.getYear());
        if (request.getDirector() != null) m.setDirector(request.getDirector());
        if (request.getCoverImage() != null) {
            m.setCoverImage(request.getCoverImage());
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

    public Movie findLatest() {
        return movieRepo.findAllByOrderByYearDesc().getFirst();
    }
}
