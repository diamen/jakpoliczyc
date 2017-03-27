package pl.jakpoliczyc.web.errors;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        return new ResponseEntity<>(
          new ErrorDetail(
                  "Resource Not Found",
                  HttpStatus.NOT_FOUND.value(),
                  rnfe.getMessage(),
                  new Date().getTime(),
                  rnfe.getClass().getName()
          ), null, HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<?> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException acnte,
                                                                              HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorDetail(
                        "Authentication Credentials Not Found",
                        HttpStatus.UNAUTHORIZED.value(),
                        acnte.getMessage(),
                        new Date().getTime(),
                        acnte.getClass().getName()
                ), null, HttpStatus.UNAUTHORIZED
        );
    }

}