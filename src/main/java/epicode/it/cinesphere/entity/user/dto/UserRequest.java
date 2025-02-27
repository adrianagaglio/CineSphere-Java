package epicode.it.cinesphere.entity.user.dto;

import epicode.it.cinesphere.auth.dto.RegisterRequest;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
}
