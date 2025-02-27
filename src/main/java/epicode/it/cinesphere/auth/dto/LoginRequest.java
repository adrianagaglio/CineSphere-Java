package epicode.it.cinesphere.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "Email o username required")
    private String identifier;

    @NotNull(message = "Password is required")
    private String password;
}
