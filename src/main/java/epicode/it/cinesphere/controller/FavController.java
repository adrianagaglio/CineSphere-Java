package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.user.IGetUserResponse;
import epicode.it.cinesphere.entity.user.UpdateFavRequest;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourites")
@RequiredArgsConstructor
public class FavController {

    private final UserService userSvc;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IGetUserResponse> updateFavourite(@RequestBody UpdateFavRequest request) throws Exception {
        return new ResponseEntity<>(userSvc.updateFav(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getFavMovies(@RequestParam Long id) {
        return new ResponseEntity<>(userSvc.findFavMoviesByUserId(id), HttpStatus.OK);
    }


}
