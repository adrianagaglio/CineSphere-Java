package epicode.it.cinesphere.entity.movie;

import epicode.it.cinesphere.entity.movie.dto.AddMovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody AddMovieRequest movie) {
        return new ResponseEntity<>(movieService.newMovie(movie), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie request) {
        return new ResponseEntity<>(movieService.update(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMovie(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", movieService.delete(id));
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/latest")
    public ResponseEntity<Movie> getLatestMovie() {
        return new ResponseEntity<>(movieService.findLatest(), HttpStatus.OK);
    }
}
