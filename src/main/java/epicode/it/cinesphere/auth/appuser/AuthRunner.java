package epicode.it.cinesphere.auth.appuser;

import epicode.it.cinesphere.auth.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Order(1)
public class AuthRunner implements ApplicationRunner {
    private final AppUserSvc appUserSvc;
    private final PasswordEncoder pwdEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {



        Optional<AppUser> admin = appUserSvc.findByEmailOrUsername("admin@mail.com");
        if (admin.isEmpty()) {
            RegisterRequest adminRequest = new RegisterRequest("admin@mail.com","admin", "adminpwd");
            appUserSvc.registerUser(adminRequest);
        }


//        Optional<AppUser> doctor = appUserSvc.findByEmail("doctor@mail.com");
//        if (doctor.isEmpty()) {
//            RegisterRequest doctorRequest = new RegisterRequest("doctor@mail.com", "doctorpwd");
//            appUserSvc.registerDoctor(doctorRequest);
//        }
//
//        Optional<AppUser> patient = appUserSvc.findByEmail("patient@mail.com");
//        if (patient.isEmpty()) {
//            RegisterRequest patientRequest = new RegisterRequest("patient@mail.com", "patientpwd");
//            appUserSvc.registerPatient(patientRequest);
//        }

    }
}
