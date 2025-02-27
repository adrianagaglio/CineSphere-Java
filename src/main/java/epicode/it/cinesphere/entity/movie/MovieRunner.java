package epicode.it.cinesphere.entity.movie;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.actor.ActorRepo;
import epicode.it.cinesphere.entity.actor.dto.AddActorRequest;
import epicode.it.cinesphere.entity.genres.dto.AddGenre;
import epicode.it.cinesphere.entity.movie.dto.AddMovieRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieRunner implements ApplicationRunner {
    private final MovieService movieSvc;
    private final Logger logger;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(movieSvc.count() == 0) {

            String csvFile = "src/main/resources/movies.csv";

            try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
                List<String[]> rows = csvReader.readAll();

                // salta l'intestazione partendo da indice 1
                for (int i = 1; i < rows.size(); i++) {
                    String[] row = rows.get(i);

                    AddMovieRequest request = new AddMovieRequest();

                    request.setTitle(row[1]);
                    request.setDescription(row[2]);
                    request.setCoverImage(row[3]);
                    request.setYear(Integer.parseInt((row[4])));
                    request.setDirector(row[5]);
                    List<String> actors = Arrays.stream(row[6].split(";")).toList();
                    actors.forEach(a -> {
                        AddActorRequest ar = new AddActorRequest(a.split(" ")[0], a.split(" ")[1]);
                        request.getActors().add(ar);
                    });

                    List<String> genres = Arrays.stream(row[7].split(";")).toList();

                    genres.forEach(g -> {
                        AddGenre genre = new AddGenre(g);
                        request.getGenres().add(genre);
                    });

                    movieSvc.newMovie(request);
                }

                System.out.println("===> Import completed!");

            } catch (CsvException e) {
                logger.error(e.getMessage());
            }

        }

    }
}
