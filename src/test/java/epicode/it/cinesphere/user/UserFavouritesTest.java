package epicode.it.cinesphere.user;

import com.github.javafaker.Faker;
import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieService;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserFavouritesTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private Faker faker;

    @Test
    @DisplayName("Test aggiungi e rimuovi preferito")
    @Transactional
    public void testAddFavourite() {
        User u = new User();

        u.setFirstName("Luca");
        u.setLastName("Dinoia");
        u.setUsername("lukas");
        u.setEmail("lukas@mail.com");
        u=userService.save(u);

        Movie m = new Movie();

        m.setTitle(faker.book().title());
        m.setYear(2023);
        m=movieService.save(m);

        userService.addFav(u,m);

        User updatedUser = userService.findById(u.getId());

        assertTrue(updatedUser.getFavMovies().contains(m));

        Movie m2 = new Movie();
        m2.setTitle(faker.book().title());
        m2.setYear(1995);
        m2=movieService.save(m2);

        userService.addFav(u,m2);

        userService.deleteFav(u,m);

        User user = userService.findById(u.getId());

        assertTrue(user.getFavMovies().contains(m2));
        assertEquals(user.getFavMovies().size(), 1);
        assertFalse(user.getFavMovies().contains(m));
    }


}
