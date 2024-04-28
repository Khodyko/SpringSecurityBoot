package by.khodyko.different.securities.boot.db.security;

import by.khodyko.different.securities.boot.db.service.UserService;
import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class FailureAttemptsFilter extends GenericFilterBean {

    private UserService userService;

    @Autowired
    public FailureAttemptsFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
//        String userName = request.getParameter("username");
//        LoginAttempts loginAttempts =userService.getUserByUserName(userName)
//                .map(user-> user.getLoginAttempts()).orElse(null);
//        if(loginAttempts!=null && loginAttempts.getFailedLoginAttempts()<3){
//            chain.doFilter(request, response);
//        } else{

        chain.doFilter(request, response);
//        }


    }
}
