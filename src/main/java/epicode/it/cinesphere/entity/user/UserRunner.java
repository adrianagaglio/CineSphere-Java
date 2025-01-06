package epicode.it.cinesphere.entity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRunner implements ApplicationRunner {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userService.count() == 0) {
            User u = new User();
            u.setFirstName("admin");
            u.setLastName("admin");
            u.setUsername("admin");
            u.setEmail("admin@mail");
            u.setPassword(passwordEncoder.encode("admin"));
            u=userService.save(u);
        }
    }
}
