package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.auth.AuthService;
import epicode.it.cinesphere.entity.auth.LoginResponse;
import epicode.it.cinesphere.entity.auth.RegisterRequest;
import epicode.it.cinesphere.entity.user.IGetUserResponse;
import epicode.it.cinesphere.entity.auth.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public IGetUserResponse createUser(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PutMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) throws IllegalAccessException {
        return authService.login(request);
    }
}
