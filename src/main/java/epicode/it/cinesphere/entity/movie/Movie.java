package epicode.it.cinesphere.entity.movie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.genres.Genre;
import epicode.it.cinesphere.entity.rate.Rate;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private String description;

    private int year;

    @Column(name="cover_image")
    private String coverImage;

    private String director;

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Rate> rates = new ArrayList<>();

}