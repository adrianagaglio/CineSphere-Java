package epicode.it.cinesphere.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Message {
    private String message;
    private HttpStatus status;
}
