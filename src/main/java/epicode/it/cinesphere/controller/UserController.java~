package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.user.IGetUserResponse;
import epicode.it.cinesphere.entity.user.UpdateUserRequest;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import org.hibernate.sql.Update;
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
    public IGetUserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest user) throws Exception {
        return userService.update(user);
    }

    // DELETE prodotto
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) throws IllegalAccessException {
            return userService.delete(id);

    }
}
