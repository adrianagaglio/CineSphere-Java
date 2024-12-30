package epicode.it.cinesphere.entity.movie;

import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.actor.GetActorRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddMovieRequest {
    private String title;

    private String description;

    private int year;

    private List<String> coverImage;

    private String director;

    private List<String> genres;

    private List<GetActorRequest> actors = new ArrayList<>();
}
