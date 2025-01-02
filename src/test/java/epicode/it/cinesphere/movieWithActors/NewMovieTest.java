package epicode.it.cinesphere.movieWithActors;

import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.actor.ActorService;
import epicode.it.cinesphere.entity.actor.GetActorRequest;
import epicode.it.cinesphere.entity.movie.AddMovieRequest;
import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NewMovieTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @Test
    @Transactional
    public void testNewMovie() throws Exception {
        AddMovieRequest m = new AddMovieRequest();
        m.setTitle("Inception");
        m.setDescription("A skilled thief, the best in the dangerous art of extraction, steals secrets from within the subconscious during the dream state.");

        GetActorRequest a1 = new GetActorRequest();
        a1.setName("Leonardo");
        a1.setSurname("DiCaprio");
        GetActorRequest a2 = new GetActorRequest();
        a2.setName("Joseph");
        a2.setSurname("Gordon-Levitt");
        GetActorRequest a3 = new GetActorRequest();
        a3.setName("Elliot");
        a3.setSurname("Page");
        m.getActors().addAll(List.of(a1, a2, a3));

        Actor actor1 = actorService.findActorByNameAndSurname(a1.getName(), a1.getSurname());
        Actor actor2 = actorService.findActorByNameAndSurname(a2.getName(), a2.getSurname());
        Actor actor3 = actorService.findActorByNameAndSurname(a3.getName(), a3.getSurname());
        System.out.println(actor1);
        System.out.println(actor2);
        System.out.println(actor3);

        Movie movie = movieService.newMovie(m);
        Movie managed = movieService.findById(movie.getId());

        System.out.println(movie);

        assertTrue(managed.getActors().contains(actor1) && managed.getActors().contains(actor2) && managed.getActors().contains(actor3));


    }
}
