package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FavController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addFavourite(@PathParam("id") Long id, @RequestBody Movie request) throws Exception {
      return userService.addFav(id, request);
    }
}
