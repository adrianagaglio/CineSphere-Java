package epicode.it.cinesphere.entity.movie;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import epicode.it.cinesphere.entity.actor.dto.AddActorRequest;
import epicode.it.cinesphere.entity.genres.dto.AddGenre;
import epicode.it.cinesphere.entity.movie.dto.AddMovieRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieRunner implements ApplicationRunner {
    private final MovieService movieSvc;
    private final Logger logger;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (movieSvc.count() == 0) {
            // Inizializziamo le variabili per gestire le diverse possibilità
            Reader csvReader = null;
            boolean fileFound = false;

            // POSSIBILITÀ 1: File nel classpath (all'interno del JAR)
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream("movies.csv");
                if (is != null) {
                    logger.info("File CSV trovato nel classpath");
                    csvReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                    fileFound = true;
                }
            } catch (Exception e) {
                logger.warn("Errore nel tentativo di caricare il file dal classpath: " + e.getMessage());
            }

            // POSSIBILITÀ 2: File in app/resources/movies.csv
            if (!fileFound) {
                File fileInAppResources = new File("app/resources/movies.csv");
                if (fileInAppResources.exists()) {
                    logger.info("File CSV trovato in app/resources");
                    csvReader = new FileReader(fileInAppResources, StandardCharsets.UTF_8);
                    fileFound = true;
                }
            }

            // POSSIBILITÀ 3: File in src/main/resources/movies.csv
            if (!fileFound) {
                File fileInSrcResources = new File("src/main/resources/movies.csv");
                if (fileInSrcResources.exists()) {
                    logger.info("File CSV trovato in src/main/resources");
                    csvReader = new FileReader(fileInSrcResources, StandardCharsets.UTF_8);
                    fileFound = true;
                }
            }

            // Verifica se è stato trovato il file
            if (!fileFound) {
                logger.error("File movies.csv non trovato in nessuno dei percorsi cercati");
                return; // Uscita anticipata se il file non è stato trovato
            }

            // Elabora il file CSV
            try (CSVReader reader = new CSVReader(csvReader)) {
                List<String[]> rows = reader.readAll();

                // Salta l'intestazione partendo da indice 1
                for (int i = 1; i < rows.size(); i++) {
                    String[] row = rows.get(i);

                    if (row.length < 8) {
                        logger.warn("Riga " + i + " ha un formato non valido, saltata");
                        continue; // Salta righe malformate
                    }

                    AddMovieRequest request = new AddMovieRequest();

                    request.setTitle(row[1]);
                    request.setDescription(row[2]);
                    request.setCoverImage(row[3]);

                    try {
                        request.setYear(Integer.parseInt(row[4]));
                    } catch (NumberFormatException e) {
                        logger.warn("Anno non valido per il film " + row[1] + ": " + row[4]);
                        continue; // Salta il film con anno non valido
                    }

                    request.setDirector(row[5]);

                    // Gestione attori
                    List<String> actors = Arrays.stream(row[6].split(";")).toList();
                    actors.forEach(a -> {
                        String[] nameParts = a.split(" ", 2); // Gestisce casi con più spazi nel nome
                        String firstName = nameParts[0];
                        String lastName = nameParts.length > 1 ? nameParts[1] : "";
                        AddActorRequest ar = new AddActorRequest(firstName, lastName);
                        request.getActors().add(ar);
                    });

                    // Gestione generi
                    List<String> genres = Arrays.stream(row[7].split(";")).toList();
                    genres.forEach(g -> {
                        AddGenre genre = new AddGenre(g);
                        request.getGenres().add(genre);
                    });

                    try {
                        movieSvc.newMovie(request);
                        logger.debug("Film aggiunto: " + request.getTitle());
                    } catch (Exception e) {
                        logger.error("Errore nell'aggiunta del film " + request.getTitle() + ": " + e.getMessage());
                    }
                }

                logger.info("===> Import completato con successo!");

            } catch (CsvException e) {
                logger.error("Errore durante la lettura del CSV: " + e.getMessage(), e);
            } catch (Exception e) {
                logger.error("Errore imprevisto durante l'importazione: " + e.getMessage(), e);
            } finally {
                try {
                    if (csvReader != null) {
                        csvReader.close();
                    }
                } catch (Exception e) {
                    logger.warn("Errore nella chiusura del reader: " + e.getMessage());
                }
            }
        } else {
            logger.info("Database già popolato, importazione CSV saltata");
        }
    }
}