package epicode.it.cinesphere.entity.auth.dto;

import epicode.it.cinesphere.entity.user.dto.IGetUserResponse;
import lombok.Data;


@Data
public class LoginResponse {
    private String token;
    private IGetUserResponse user;

    public LoginResponse(String token, IGetUserResponse user) {
        this.token = token;
        this.user = user;
    }
}
