package epicode.it.cinesphere.entity.genres;

import epicode.it.cinesphere.entity.genres.dto.AddGenre;
import epicode.it.cinesphere.entity.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepo genreRepo;

    public Genre addGenre(AddGenre request) {
        Genre g = new Genre();
        if (getGenre(request.getName()) == null) {
            BeanUtils.copyProperties(request, g);
            return genreRepo.save(g);
        } else {
            return getGenre(request.getName());
        }

    }

    public Genre getGenre(String name) {
        return genreRepo.findFirstByName(name).orElse(null);
    }
}
