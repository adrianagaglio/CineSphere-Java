package epicode.it.cinesphere.entity.auth;

import epicode.it.cinesphere.entity.user.IGetUserResponse;
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
