package epicode.it.cinesphere.entity.rate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="rates")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @ManyToOne
    private User user;

    private int vote;

    @ManyToOne
    @JsonManagedReference
    private Movie movie;

}