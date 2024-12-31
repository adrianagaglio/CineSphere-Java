package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.user.IGetUserResponse;
import epicode.it.cinesphere.entity.user.UpdateFavRequest;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourites")
public class FavController {

    @Autowired
    private UserService userService;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public IGetUserResponse updateFavourite(@RequestBody UpdateFavRequest request) throws Exception {
      return userService.updateFav(request);
    }

    @GetMapping
    public List<Movie> getFavMovies(@RequestParam Long id) throws Exception {
        return userService.findFavMoviesByUserId(id);
    }


}
