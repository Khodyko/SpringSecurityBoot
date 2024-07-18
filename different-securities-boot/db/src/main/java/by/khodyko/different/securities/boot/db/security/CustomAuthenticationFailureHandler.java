package by.khodyko.different.securities.boot.db.security;

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

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

   private LoginAttemptService loginAttemptService;

    public CustomAuthenticationFailureHandler(LoginAttemptService loginAttemptService) {
        this.loginAttemptService=loginAttemptService;
    }

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
