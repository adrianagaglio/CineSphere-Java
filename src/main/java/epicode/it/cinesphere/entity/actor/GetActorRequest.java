package epicode.it.cinesphere.entity.actor;

import lombok.Data;

@Data
public class GetActorRequest {
    private String name;
    private String surname;

    public GetActorRequest(String fullName) {
        String[] parts = fullName.split(" ");
        if (parts.length > 1) {
            this.name = parts[0];
            this.surname = parts[parts.length - 1];
        } else {
            throw new IllegalArgumentException("Invalid actor name format: " + fullName);
        }
    }
}
