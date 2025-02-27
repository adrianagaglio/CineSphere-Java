package epicode.it.cinesphere.entity.user.dto;

import lombok.Data;

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
