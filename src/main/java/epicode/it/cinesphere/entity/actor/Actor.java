package epicode.it.cinesphere.entity.actor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import epicode.it.cinesphere.entity.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String surname;

    @ManyToMany(mappedBy = "actors")
    @JsonBackReference
    private List<Movie> movies = new ArrayList<>();

}