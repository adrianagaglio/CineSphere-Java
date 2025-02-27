package epicode.it.cinesphere.entity.rate;

import epicode.it.cinesphere.entity.movie.Movie;
import epicode.it.cinesphere.entity.movie.MovieService;
import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepo rateRepo;
    private final MovieService movieService;
    private final UserService userService;

    public IRateResponse createRate(AddRateRequest request)  {
        User u = userService.findById(request.getUserId());
        Movie m = movieService.findById(request.getMovieId());
        Rate r = new Rate();
        r.setUser(u);
        u.getRates().add(r);
        r.setMovie(m);
        m.getRates().add(r);
        r.setVote(request.getVote());
        r = save(r);
        return findIRateResponseBy(r.getId());
    }

    public List<IRateResponse> findAllIRateResponse() {
        return rateRepo.findAllIRateResponseBy();
    }

    public IRateResponse findIRateResponseBy(Long id) {
        return rateRepo.findIRateResponseBy(id);
    }

    public Rate findById(Long id) {
        return rateRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Rate not found"));
    }

    public Rate save(Rate rate) {
        return rateRepo.save(rate);
    }

    public List<Rate> save(List<Rate> rates) {
        return rateRepo.saveAll(rates);
    }

    public int count() {
        return (int) rateRepo.count();
    }

    public List<Rate> findAll() {
        return rateRepo.findAll();
    }

    public void delete(Rate rate) {
        rateRepo.delete(rate);
    }

    public void delete(Long id) throws Exception {
        Rate r = findById(id);
        rateRepo.deleteById(id);
    }

    public List<IRateResponse> getRatesByUser(Long userId) {
        return rateRepo.findByUserId(userId);
    }

    public List<IRateResponse> getRatesByMovie(Long movieId) {
        return rateRepo.findByMovieId(movieId);
    }
}
