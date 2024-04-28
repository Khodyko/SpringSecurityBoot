package by.khodyko.different.securities.boot.db.controller;

import by.khodyko.different.securities.boot.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }

    @GetMapping(value = "/account-locked")
    public String locked() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "account-locked";
    }
}