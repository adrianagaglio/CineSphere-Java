package epicode.it.cinesphere.entity.actor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddActorRequest {
    private String name;
    private String surname;
}
