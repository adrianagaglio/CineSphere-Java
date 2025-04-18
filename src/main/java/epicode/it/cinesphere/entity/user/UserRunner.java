package epicode.it.cinesphere.entity.user;

import com.github.javafaker.Faker;
import epicode.it.cinesphere.auth.appuser.AppUser;
import epicode.it.cinesphere.auth.appuser.AppUserSvc;
import epicode.it.cinesphere.auth.dto.RegisterRequest;
import epicode.it.cinesphere.entity.user.dto.UserRequest;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserRunner implements ApplicationRunner {
    private final UserService userService;
    private final AppUserSvc appUserSvc;
    private final Faker faker;
    private final Logger logger;

    @Override
    public void run(ApplicationArguments args) throws Exception {



        if(userService.count() == 0) {

            Optional<AppUser> admin = appUserSvc.findByEmailOrUsername("admin@mail.com");
            if (admin.isEmpty()) {

                UserRequest request = new UserRequest();
                request.setFirstName(faker.name().firstName());
                request.setLastName(faker.name().firstName());

                RegisterRequest adminRequest = new RegisterRequest("admin@mail.com","admin", "adminpwd");

                adminRequest.setUser(request);

                try {
                    appUserSvc.registerAdmin(adminRequest);

                } catch (EntityExistsException e) {
                    logger.info(adminRequest.toString());
                    logger.error(e.getMessage());
                }
            }


            for(int i = 0; i < 10; i++) {

                UserRequest request = new UserRequest();
                request.setFirstName(faker.name().firstName());
                request.setLastName(faker.name().lastName());

                String lastname = request.getLastName().replace(" ", "").replace("'","");

                RegisterRequest register = new RegisterRequest();
                register.setEmail(request.getFirstName().toLowerCase() + "." + lastname.toLowerCase() + "@mail.com");
                register.setUsername(request.getFirstName().toLowerCase() + lastname.toLowerCase());
                register.setPassword("password");

                register.setUser(request);

                try {
                appUserSvc.registerUser(register);

                } catch (EntityExistsException e) {
                    logger.info(register.toString());
                    logger.error(e.getMessage());
                }



            }

        }

    }
}
