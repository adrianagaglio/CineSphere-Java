package epicode.it.cinesphere.entity.rate;

import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@NamedQuery(name="findAll_Rate", query="SELECT a FROM Rate a")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @OneToOne
    private User user;

    private int vote;

    @OneToOne
    private Movie movie;

}