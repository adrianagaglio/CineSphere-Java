package epicode.it.cinesphere.movie;

import com.github.javafaker.Faker;
import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MovieTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private Faker faker;


    @Test
    @DisplayName("Test creazione movie")
    @Transactional
    public void testCreateMovie() {
        int actualMovies = movieService.count();
        Movie m = new Movie();
        m.setTitle("Pulp Fiction");
        m.setDescription("lorem ipsum");
        m.setYear(1994);
        m = movieService.save(m);

        Movie savedMovie = movieService.findById(m.getId());

        int updatedMovies = movieService.count();

        System.out.println(savedMovie);

        assertTrue(savedMovie != null);
        assertEquals(actualMovies + 1, updatedMovies);
        assertEquals(savedMovie.getId(), m.getId());
        assertEquals(savedMovie.getTitle(), "Pulp Fiction");

        movieService.delete(savedMovie);

        assertEquals(movieService.count(), actualMovies);
    }

    @Test
    @DisplayName("Test ricerca per titolo")
    @Transactional
    public void testFindByTitle() {
        Movie m1 = new Movie();
        m1.setTitle("pulp fiction");
        m1 = movieService.save(m1);

        Movie m2 = new Movie();
        m2.setTitle("jackie brown");
        m2 = movieService.save(m2);

        Movie savedMovie1 = movieService.findByTitle("Pulp fiction");

        System.out.println(savedMovie1);

        assertTrue(savedMovie1 != null);
        assertEquals(savedMovie1.getTitle(), "pulp fiction");
    }

    @Test
    @DisplayName("Test ricerca per anno")
    @Transactional
    public void testFindByYear() {
        List<Movie> movies = new ArrayList<>();

        int actualCount = movieService.count();

        for (int i = 0; i < 3; i++) {
            Movie m = new Movie();
            m.setTitle(faker.book().title());
            m.setYear(1989);
            movies.add(m);
        }

        Movie m = new Movie();
        m.setTitle(faker.book().title());
        m.setYear(2024);
        movies.add(m);

        movies=movieService.save(movies);


        assertEquals(actualCount + 4, movieService.count());

        List<Movie> byYear = movieService.findByYear(1989);

        System.out.println(byYear);

        assertEquals(byYear.size(), 3);


    }
}
