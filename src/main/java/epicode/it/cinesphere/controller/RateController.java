package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.movie.MovieService;
import epicode.it.cinesphere.entity.rate.AddRateRequest;
import epicode.it.cinesphere.entity.rate.IRateResponse;
import epicode.it.cinesphere.entity.rate.Rate;
import epicode.it.cinesphere.entity.rate.RateService;
import epicode.it.cinesphere.entity.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rates")
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<IRateResponse> getRates() {
        return rateService.findAllIRateResponse();
    }

    @GetMapping("/by-user/{userId}")
    public List<IRateResponse> getRatesByUser(@PathVariable Long userId) {
        return rateService.getRatesByUser(userId);
    }

    @GetMapping("/by-movie/{movieId}")
    public List<IRateResponse> getRatesByMovie(@PathVariable Long movieId) {
        return rateService.getRatesByMovie(movieId);
    }

    @PostMapping
    public IRateResponse createRate(@RequestBody AddRateRequest request) throws Exception {
        return rateService.createRate(request);
    }

}
