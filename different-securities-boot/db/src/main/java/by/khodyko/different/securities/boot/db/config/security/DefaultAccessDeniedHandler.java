package by.khodyko.different.securities.boot.db.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This class is a default implementation of the AccessDeniedHandler interface.
 * It handles the case when a user tries to access a resource they are not authorized to access.
 */
@Component
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Handles the access denied event.
     * In this implementation, it sends a 404 Not Found response to the client.
     *
     * @param request               the HttpServletRequest object
     * @param response              the HttpServletResponse object
     * @param accessDeniedException the AccessDeniedException that occurred
     * @throws IOException          if an I/O error occurs
     * @throws ServletException     if a servlet-related error occurs
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
