package epicode.it.cinesphere.user;

import epicode.it.cinesphere.entity.auth.AuthService;
import epicode.it.cinesphere.entity.auth.RegisterRequest;
import epicode.it.cinesphere.entity.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testRegister() {
        RegisterRequest newUser = new RegisterRequest();
        newUser.setEmail("gverdi@mail.com");
        newUser.setUsername("gverdi");
        newUser.setPassword("password");
        newUser.setFirstName("Giovanni");
        newUser.setLastName("Verdi");
        IGetUserResponse u = authService.register(newUser);

        User foundUser = userService.findById(u.getId());

        assertEquals(foundUser.getId(),u.getId());

        User foundByEmailOrUsername = userRepo.findFirstByEmailOrUsername(foundUser.getUsername());

        assertTrue(foundByEmailOrUsername != null);

    }
}
