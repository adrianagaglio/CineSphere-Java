package epicode.it.cinesphere.entity.actor;

import epicode.it.cinesphere.entity.actor.dto.AddActorRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
        return actorRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Actor not found"));
    }

    public String delete(Actor actor) {
        actorRepo.delete(actor);
        return "Actor deleted successfully";
    }

    public String delete(Long id) {
        Actor a = findById(id);

        delete(a);

        return "Actor deleted successfully";
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

    public Actor saveActor(AddActorRequest request) {
        if (findActorByNameAndSurname(request.getName(), request.getSurname()) != null)
            throw new EntityExistsException("Actor already exists");

        Actor a = new Actor();
        a.setName(request.getName());
        a.setSurname(request.getSurname());
        return actorRepo.save(a);

    }
}