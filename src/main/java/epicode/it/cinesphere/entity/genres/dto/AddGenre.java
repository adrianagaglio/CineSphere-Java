package epicode.it.cinesphere.entity.genres.dto;

import epicode.it.cinesphere.entity.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddGenre {
    private String name;
    private List<Movie> movies = new ArrayList<>();

    public AddGenre(String name) {
        this.name = name;
    }
}
