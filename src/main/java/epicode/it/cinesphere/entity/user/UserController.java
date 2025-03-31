package epicode.it.cinesphere.entity.user;

import epicode.it.cinesphere.entity.user.dto.IGetUserResponse;
import epicode.it.cinesphere.entity.user.dto.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // GET tutti user
    @GetMapping
    public ResponseEntity<List<IGetUserResponse>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<IGetUserResponse> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        return ResponseEntity.ok(userService.findByUsername(userDetails.getUsername()));

    }

    // GET user by id
    @GetMapping("/{id}")
    public ResponseEntity<IGetUserResponse> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

//    // PUT aggiorna
//    @PutMapping("/{id}")
//    public ResponseEntity<IGetUserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest user) {
//        return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
//    }

    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", userService.delete(id));
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

    }
}
