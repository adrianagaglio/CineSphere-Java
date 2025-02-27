package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.user.IGetUserResponse;
import epicode.it.cinesphere.entity.user.UpdateUserRequest;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // GET user by id
    @GetMapping("/{id}")
    public ResponseEntity<IGetUserResponse> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    // PUT aggiorna
    @PutMapping("/{id}")
    public ResponseEntity<IGetUserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", userService.delete(id));
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

    }
}
