package epicode.it.cinesphere.entity.movie.dto;

import epicode.it.cinesphere.entity.actor.dto.AddActorRequest;
import epicode.it.cinesphere.entity.actor.dto.GetActorRequest;
import epicode.it.cinesphere.entity.genres.Genre;
import epicode.it.cinesphere.entity.genres.dto.AddGenre;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddMovieRequest {
    private String title;

    private String description;

    private int year;

    private String coverImage;

    private String director;

    private List<AddGenre> genres = new ArrayList<>();

    private List<AddActorRequest> actors = new ArrayList<>();
}
