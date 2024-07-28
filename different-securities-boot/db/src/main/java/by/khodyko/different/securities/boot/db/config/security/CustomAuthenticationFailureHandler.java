package by.khodyko.different.securities.boot.db.config.security;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;
import by.khodyko.different.securities.boot.db.service.LoginAttemptService;
import by.khodyko.different.securities.boot.db.util.LoginAttemptUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This custom authentication failure handler is responsible for handling login failures.
 * It interacts with the LoginAttemptService to manage the login attempt information for a user.
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

   private LoginAttemptService loginAttemptService;

    public CustomAuthenticationFailureHandler(LoginAttemptService loginAttemptService) {
        this.loginAttemptService=loginAttemptService;
    }


    /**
     * Handles the authentication failure event.
     * It checks if the login attempt is blocked, and if not, updates the login attempt information accordingly.
     *
     * @param request       the HttpServletRequest object
     * @param response      the HttpServletResponse object
     * @param exception     the AuthenticationException that occurred
     * @throws IOException  if an I/O error occurs
     * @throws ServletException if a servlet-related error occurs
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String userName = request.getParameter("username");
        LoginAttempt loginAttempt = loginAttemptService.getLoginAttemptByUserName(userName);

        if (LoginAttemptUtil.isAttemptBlocked(loginAttempt)) {
            response.sendRedirect("account-locked");
        } else {
            if(LoginAttemptUtil.isTimePastAfterLastAttempt(loginAttempt)){
                loginAttempt.setFailedLoginAttempts(1);
            } else{
                loginAttempt.setFailedLoginAttempts(loginAttempt.getFailedLoginAttempts()+1);
            }
            loginAttempt.setLastFailedLoginTime(LocalDateTime.now());
            loginAttemptService.save(loginAttempt);
            response.sendRedirect("login");
        }
    }
}
