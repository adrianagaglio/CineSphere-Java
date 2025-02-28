package epicode.it.cinesphere.entity.movie;

import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.actor.ActorService;
import epicode.it.cinesphere.entity.genres.Genre;
import epicode.it.cinesphere.entity.genres.GenreService;
import epicode.it.cinesphere.entity.movie.dto.AddMovieRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final GenreService genreService;
    private final ActorService actorService;

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

    @GetMapping("/paged")
    public ResponseEntity<Page<Movie>> pagedMovies(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(movieService.findAllPageable(pageable));
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getGenres() {
        return new ResponseEntity<>(genreService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getActors() {
        return new ResponseEntity<>(actorService.findAll(), HttpStatus.OK);
    }
}
