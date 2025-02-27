package epicode.it.cinesphere.auth.appuser;


import com.github.javafaker.App;
import epicode.it.cinesphere.auth.dto.AuthResponse;
import epicode.it.cinesphere.auth.dto.LoginRequest;
import epicode.it.cinesphere.auth.dto.RegisterRequest;
import epicode.it.cinesphere.auth.jwt.JwtTokenUtil;
import epicode.it.cinesphere.entity.user.UserRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AppUserSvc appUserSvc;
    private final UserRepo userRepo;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(appUserSvc.registerUser(registerRequest), HttpStatus.CREATED);
    }


    @PostMapping("/new-admin")
    public ResponseEntity<Map<String, String>> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        appUserSvc.registerAdmin(registerRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Admin created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = appUserSvc.authenticateUser(loginRequest);

        return ResponseEntity.ok(new AuthResponse(token, jwtTokenUtil.getRolesFromToken(token)));
    }


}
