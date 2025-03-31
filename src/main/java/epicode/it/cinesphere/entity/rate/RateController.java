package epicode.it.cinesphere.entity.rate;

import epicode.it.cinesphere.entity.movie.MovieService;
import epicode.it.cinesphere.entity.rate.dto.AddRateRequest;
import epicode.it.cinesphere.entity.rate.dto.IRateResponse;
import epicode.it.cinesphere.entity.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rates")
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;
    private final UserService userService;
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<IRateResponse>> getRates() {
        return new ResponseEntity<>(rateService.findAllIRateResponse(), HttpStatus.OK);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<IRateResponse>> getRatesByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(rateService.getRatesByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/by-movie/{movieId}")
    public ResponseEntity<List<IRateResponse>> getRatesByMovie(@PathVariable Long movieId) {
        return new ResponseEntity<>(rateService.getRatesByMovie(movieId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IRateResponse> createRate(@RequestBody AddRateRequest request) {
        return new ResponseEntity<>(rateService.createRate(request), HttpStatus.OK);
    }

}
