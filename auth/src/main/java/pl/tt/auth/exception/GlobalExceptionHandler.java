package pl.tt.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PersonNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(PersonNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDTO.builder()
                        .code("20230119:173102")
                        .message(ex.getMessage())
                        .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                        .build());
    }
    @ExceptionHandler({NotAuthorizedException.class})
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(NotAuthorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorDTO.builder()
                        .code("20230126:162013")
                        .message(ex.getMessage())
                        .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                        .build());
    }
}
