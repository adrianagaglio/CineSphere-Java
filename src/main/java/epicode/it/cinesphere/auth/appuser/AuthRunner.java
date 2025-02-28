//package epicode.it.cinesphere.auth.appuser;
//
//import com.github.javafaker.Faker;
//import epicode.it.cinesphere.auth.dto.RegisterRequest;
//
//import epicode.it.cinesphere.entity.user.dto.UserRequest;
//import jakarta.persistence.EntityExistsException;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//@Order(1)
//public class AuthRunner implements ApplicationRunner {
//    private final AppUserSvc appUserSvc;
//    private final Faker faker;
//    private final Logger logger;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//
//
//        Optional<AppUser> admin = appUserSvc.findByEmailOrUsername("admin@mail.com");
//        if (admin.isEmpty()) {
//
//            UserRequest request = new UserRequest();
//            request.setFirstName(faker.name().firstName());
//            request.setLastName(faker.name().firstName());
//
//            RegisterRequest adminRequest = new RegisterRequest("admin@mail.com","admin", "adminpwd");
//
//            adminRequest.setUser(request);
//
//            try {
//                appUserSvc.registerAdmin(adminRequest);
//
//            } catch (EntityExistsException e) {
//                logger.info(adminRequest.toString());
//                logger.error(e.getMessage());
//            }
//        }
//
//
//
//
//    }
//}
