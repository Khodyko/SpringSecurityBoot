package by.khodyko.different.securities.boot.db.security;

import by.khodyko.different.securities.boot.db.model.User;
import by.khodyko.different.securities.boot.db.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.Optional;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private UserService userService;

    public CustomAuthenticationFailureHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String userName = request.getParameter("username");
        Optional<User> user=userService.getUserByUserName(userName);
        if(user.isPresent() && !userService.isAttemptsLoginAvailable(user.get().getLoginAttempt())){
            response.sendRedirect("/db/account-locked");
        } else{
            userService.increaseLoginAttempts(userName);
        }
    }
}
