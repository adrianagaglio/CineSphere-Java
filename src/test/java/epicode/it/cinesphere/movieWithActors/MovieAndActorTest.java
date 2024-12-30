package epicode.it.cinesphere.movieWithActors;

import com.github.javafaker.Faker;
import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.actor.ActorService;
import epicode.it.cinesphere.entity.movie.AddMovieRequest;
import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieAndActorTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private Faker faker;

    @Test
    @DisplayName("Test creazione movie con actor")
    @Transactional
    public void testCreateMovieWithActor() {
        List<Actor> actors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
        Actor a = new Actor();
        a.setName(faker.name().firstName());
        a.setSurname(faker.name().lastName());
        actors.add(a);
        }
        actorService.save(actors);

        int actorCount = actorService.count();

        assertEquals(actorCount, 3);

        AddMovieRequest m = new AddMovieRequest();
        m.setTitle(faker.book().title());
        for (Actor a : actors) {
            m.getActors().add(a.getActorName());
        }

        Movie savedMovie;

        try {
            savedMovie = movieService.newMovie(m);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(savedMovie.getActors().size(), 3);

        Movie foundMovie = movieService.findById(savedMovie.getId());
        assertEquals(savedMovie.getId(), foundMovie.getId());
        assertEquals(foundMovie.getActors().size(), actors.size());

        System.out.println("===> Salvato: " + savedMovie);
        System.out.println("===> Recuperato: " + foundMovie);
    }

}
