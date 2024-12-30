package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.movie.AddMovieRequest;
import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieService;
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
        return movieService.findById(1L);
    }

    @PostMapping
    public String addMovie(@RequestBody AddMovieRequest movie) {
        Movie m = movieService.newMovie(movie);
        if (m!=null)
            return "Movie added successfully";
        else
            return "Error while adding movie";
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
