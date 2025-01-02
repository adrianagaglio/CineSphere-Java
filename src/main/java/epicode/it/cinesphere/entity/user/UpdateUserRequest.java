package epicode.it.cinesphere.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class UpdateUserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String actualPassword;
    private String newPassword;
}
