package epicode.it.cinesphere.auth.dto;



import epicode.it.cinesphere.entity.user.User;
import epicode.it.cinesphere.entity.user.dto.UserRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull(message = "Email richiesta")
    private String email;

    @NotNull(message = "Username richiesta")
    private String username;

    private String password;

    private UserRequest user;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
