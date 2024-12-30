package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.user.IGetUserResponse;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    // GET tutti user
    @GetMapping
    public List<IGetUserResponse> getUsers() {
        return userService.getAllUsers();
    }

    // GET user by id
    @GetMapping("/{id}")
    public IGetUserResponse getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // PUT aggiorna
    @PutMapping("/{id}")
    public IGetUserResponse updateUser(@PathVariable Long id, @RequestBody User user) {
        if(userService.findById(id) == null)
            throw new IllegalArgumentException("User not found");
        User managedUser = userService.findById(id);
        return userService.update(managedUser);
    }

    // DELETE prodotto
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        try {
            return userService.delete(id);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
