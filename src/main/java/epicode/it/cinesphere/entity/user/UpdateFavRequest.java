package epicode.it.cinesphere.entity.user;

import lombok.Data;

@Data
public class UpdateFavRequest {
    private Long userId;
    private Long movieId;
}
