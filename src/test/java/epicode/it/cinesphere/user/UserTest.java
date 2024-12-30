package epicode.it.cinesphere.user;

import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Test creazione utenti")
    public void testCreateUsers() {

        int actualUsers = userService.count();

        User newUser = new User();
        newUser.setFirstName("mario");
        newUser.setLastName("rossi");
        newUser.setEmail("mr@mail.com");
        newUser.setUsername("12345678");
        newUser=userService.save(newUser);

        User u = userService.findById(newUser.getId());

        int updatedUsers = userService.count();

        assertEquals(actualUsers + 1, updatedUsers);
        assertEquals(u.getId(), newUser.getId());

        userService.delete(u);

        assertEquals(userService.count(), actualUsers);
    }

    @Test
    @DisplayName("Test ricerca per username")
    @Transactional
    public void testFindByUsername() {
        User u = new User();
        u.setFirstName("luigi");
        u.setLastName("bianchi");
        u.setUsername("lbianchi");
        u.setEmail("lbianchi@mail.com");
        u=userService.save(u);

        User savedUser = userService.findByUsername("lBianchi");

        System.out.println(savedUser);

        assertTrue(savedUser != null);
        assertEquals(savedUser.getId(), u.getId());
        assertEquals(savedUser.getUsername(), "lbianchi");
    }

    @Test
    @DisplayName("Test ricerca per email")
    @Transactional
    public void testFindByEmail() {
        User u = new User();
        u.setFirstName("giovanni");
        u.setLastName("verdi");
        u.setUsername("gverdi");
        u.setEmail("gverdi@mail.com");
        u=userService.save(u);

        User savedUser = userService.findByEmail("GVERDI@mail.com");

        System.out.println(savedUser);

        assertTrue(savedUser != null);
        assertEquals(savedUser.getId(), u.getId());
        assertEquals(savedUser.getEmail(), "gverdi@mail.com");
    }

    @Test
    @DisplayName("Test ricerca per nome e cognome")
    @Transactional
    public void testFindByFirstNameAndLastName() {
        User u = new User();
        u.setFirstName("maria");
        u.setLastName("Rossetti");
        u.setUsername("mross");
        u.setEmail("mross@mail.com");
        u=userService.save(u);

        List<User> savedUser = userService.findByFirstNameAndLastName("Maria", "rossetti");

        System.out.println(savedUser);

        assertTrue(savedUser != null);
        assertEquals(savedUser.size(), 1);


    }

}
