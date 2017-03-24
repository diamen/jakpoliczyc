package pl.jakpoliczyc.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import pl.jakpoliczyc.web.errors.ErrorDetail;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

class AuthenticationUtils {

    public static void sendError(HttpServletResponse response, Exception exception, HttpStatus status) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.getType());
        response.setStatus(status.value());
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(new ErrorDetail(
                "Authentication Failed",
                status.value(),
                exception.getMessage(),
                new Date().getTime(),
                exception.getClass().getName()
        )));
        writer.flush();
        writer.close();
    }

}
