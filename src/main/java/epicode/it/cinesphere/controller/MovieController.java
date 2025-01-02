package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.movie.AddMovieRequest;
import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public Movie addMovie(@RequestBody AddMovieRequest movie) throws Exception {
        return movieService.newMovie(movie);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie request) throws Exception {
        return movieService.update(request);
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return "Movie deleted successfully";
    }

}
