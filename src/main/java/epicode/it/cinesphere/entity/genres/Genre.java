package epicode.it.cinesphere.entity.genres;

import com.fasterxml.jackson.annotation.JsonBackReference;
import epicode.it.cinesphere.entity.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="genres")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies = new ArrayList<>();
}
