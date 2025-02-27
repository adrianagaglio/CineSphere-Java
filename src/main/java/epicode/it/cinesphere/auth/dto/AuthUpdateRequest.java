package epicode.it.cinesphere.auth.dto;

import lombok.Data;

@Data
public class AuthUpdateRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
