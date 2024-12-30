package epicode.it.cinesphere.entity.actor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepo actorRepo;

    public Actor save(Actor actor) {
        return actorRepo.save(actor);
    }

    public List<Actor> save(List<Actor> actors) {
        return actorRepo.saveAll(actors);
    }

    public Actor findById(Long id) {
        return actorRepo.findById(id).orElse(null);
    }

    public void delete(Actor actor) {
        actorRepo.delete(actor);
    }

    public void delete(Long id) throws Exception {
        Actor a = findById(id);
        if (a != null) {
            delete(a);
        } else {
            throw new Exception("Actor not found");
        }
    }

    public List<Actor> findAll() {
        return actorRepo.findAll();
    }

    public int count() {
        return (int) actorRepo.count();
    }



    public Actor findActorByNameAndSurname(String name, String surname) {
        return actorRepo.findFirstByNameAndSurname(name, surname);
    }

    public Actor saveActor(AddActorRequest request) throws Exception {
        if(findActorByNameAndSurname(request.getName(), request.getSurname()) != null)
            throw new Exception("Actor already exists");

        Actor a = new Actor();
        a.setName(request.getName());
        a.setSurname(request.getSurname());
        return actorRepo.save(a);

    }
}