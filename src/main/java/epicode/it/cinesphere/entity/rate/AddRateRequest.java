package epicode.it.cinesphere.entity.rate;

import lombok.Data;

@Data
public class AddRateRequest {
    private Long userId;
    private Long movieId;
    private int vote;
}
