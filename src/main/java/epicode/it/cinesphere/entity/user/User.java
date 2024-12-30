package epicode.it.cinesphere.entity.user;

import epicode.it.cinesphere.entity.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String username;

    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> favMovies = new ArrayList<>();


}