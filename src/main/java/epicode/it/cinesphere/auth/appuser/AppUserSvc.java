package epicode.it.cinesphere.auth.appuser;


import epicode.it.cinesphere.auth.dto.AuthResponse;
import epicode.it.cinesphere.auth.dto.AuthUpdateRequest;
import epicode.it.cinesphere.auth.dto.LoginRequest;
import epicode.it.cinesphere.auth.dto.RegisterRequest;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Validated
public class AppUserSvc {
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder pwdEncoder;

    private final AuthenticationManager authenticationManager;
    private final epicode.it.cinesphere.auth.jwt.JwtTokenUtil jwtTokenUtil;
    private final UserRepo userRepo;

    public int count() {
        return (int) appUserRepo.count();
    }

    @Transactional
    public String registerUser(@Valid RegisterRequest request) {
        // controllo se l'email esiste
        if (appUserRepo.existsByEmailOrUsername(request.getEmail(), request.getUsername())) {
            throw new EntityExistsException("Utente già registrato");
        }


        // se non esiste, ne viene generata una temporanea
        // (per l'inserimento dei dati del paziente in fase di prenotazione)
        if (request.getPassword() == null) request.setPassword("temp_password");

        AppUser appUser = new AppUser();
        appUser.setEmail(request.getEmail());
        appUser.setUsername(request.getUsername());
        appUser.setPassword(pwdEncoder.encode(request.getPassword()));
        appUser = appUserRepo.save(appUser);

        if (request.getUser() != null) {
            User u = new User();
            BeanUtils.copyProperties(request.getUser(), u);
            u = userRepo.save(u);
            u.setAppUser(appUser);
            appUser.setUser(u);
        }

        appUser.setRoles(new HashSet<>(Set.of(Role.ROLE_PATIENT)));

        appUser = appUserRepo.save(appUser);

        return "User registered successfully";
    }

    @Transactional
    // metodo per la registrazione di un nuovo amministratore
    public String registerAdmin(@Valid RegisterRequest request) {

        if (appUserRepo.existsByEmailOrUsername(request.getEmail(), request.getUsername())) {
            throw new EntityExistsException("Utente già registrato");
        }

        AppUser appUser = new AppUser();
        appUser.setEmail(request.getEmail());
        appUser.setUsername(request.getUsername());
        appUser.setPassword(pwdEncoder.encode(request.getPassword()));
        appUser = appUserRepo.save(appUser);

        if (request.getUser() != null) {
            User u = new User();
            BeanUtils.copyProperties(request.getUser(), u);
            u = userRepo.save(u);
            u.setAppUser(appUser);
            appUser.setUser(u);
        }

        appUser.setRoles(new HashSet<>(Set.of(Role.ROLE_ADMIN)));

        appUser = appUserRepo.save(appUser);

        return "User registered successfully";
    }

    // cerca utente per email
    public Optional<AppUser> findByEmailOrUsername(String identifier) {
        return appUserRepo.findByEmailOrUsername(identifier);
    }

    // metodo per il login
    @Transactional
    public String authenticateUser(@Valid LoginRequest request) {
        try {
            AppUser u = loadUserByEmailOrUsername(request.getIdentifier());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(u.getUsername(), request.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            appUserRepo.save(u);
            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }

    // carica utente per email
    public AppUser loadUserByEmailOrUsername(String identifier) {
        return findByEmailOrUsername(identifier).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
    }

    // metodo per modificare dati di accesso utente
    @Transactional
    public AppUser updateLoginInfo(AppUser appUser, @Valid AuthUpdateRequest request) {

        if (request.getEmail() != null) {
            appUser.setEmail(request.getEmail());
        }

        if (request.getNewPassword() != null && !request.getNewPassword().isEmpty() && request.getOldPassword() != null && !request.getOldPassword().isEmpty()) {
            if (pwdEncoder.matches(request.getOldPassword(), appUser.getPassword())) {
                appUser.setPassword(pwdEncoder.encode(request.getNewPassword()));
            } else {
                throw new SecurityException("Credenziali non valide");
            }
        }
        return appUserRepo.save(appUser);
    }

    // metodo per il reset della password
    public String resetPasswordRequest(String identifier, boolean firstAccess) {
        AppUser u = loadUserByEmailOrUsername(identifier);
        String token = jwtTokenUtil.generateAccessToken(u);

        return "Richiesta inviata con successo!";
    }


}
