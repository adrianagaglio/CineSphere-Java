package epicode.it.cinesphere.controller;

import epicode.it.cinesphere.entity.actor.Actor;
import epicode.it.cinesphere.entity.actor.ActorService;
import epicode.it.cinesphere.entity.actor.AddActorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<Actor> getActors() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor getActorById(Long id) {
        return actorService.findById(id);
    }

    @PostMapping
    public Actor saveActor(@RequestBody AddActorRequest request) throws Exception {
        return actorService.saveActor(request);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(Long id) throws Exception {
        actorService.delete(id);
    }
}
