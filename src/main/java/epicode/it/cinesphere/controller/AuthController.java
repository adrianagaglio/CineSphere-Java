package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.auth.AuthService;
import epicode.it.cinesphere.entity.auth.LoginResponse;
import epicode.it.cinesphere.entity.auth.RegisterRequest;
import epicode.it.cinesphere.entity.user.IGetUserResponse;
import epicode.it.cinesphere.entity.auth.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authSvc;

    @PostMapping("/register")
    public ResponseEntity<IGetUserResponse> createUser(@RequestBody RegisterRequest request) {

        return new ResponseEntity<>(authSvc.register(request), HttpStatus.OK) ;
    }

    @PutMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authSvc.login(request), HttpStatus.OK);
    }
}
