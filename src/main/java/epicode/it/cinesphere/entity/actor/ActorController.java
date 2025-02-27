package epicode.it.cinesphere.entity.actor;

import epicode.it.cinesphere.entity.actor.dto.AddActorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorSvc;


    @GetMapping
    public ResponseEntity<List<Actor>> getActors() {
        return new ResponseEntity<>(actorSvc.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(Long id) {

        return new ResponseEntity<>(actorSvc.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Actor> saveActor(@RequestBody AddActorRequest request) {
        return new ResponseEntity<>(actorSvc.saveActor(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteActor(Long id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", actorSvc.delete(id));
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
