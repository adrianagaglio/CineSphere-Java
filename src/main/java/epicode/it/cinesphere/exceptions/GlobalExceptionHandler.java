package epicode.it.cinesphere.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jdk.jfr.Experimental;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger;

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Message> handleEntityExistsException(EntityExistsException ex) {
        Message message = new Message();
        message.setMessage(ex.getMessage());
        message.setStatus(HttpStatus.BAD_REQUEST);
        logger.error(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Message> handleEntityNotFoundException(EntityExistsException ex) {
        Message message = new Message();
        message.setMessage(ex.getMessage());
        message.setStatus(HttpStatus.NOT_FOUND);
        logger.error(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Message> handleAccessDeniedException(AccessDeniedException ex) {
        Message message = new Message();
        message.setMessage(ex.getMessage());
        message.setStatus(HttpStatus.FORBIDDEN);
        logger.error(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
}
