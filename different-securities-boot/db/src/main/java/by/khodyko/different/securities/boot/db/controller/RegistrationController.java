package by.khodyko.different.securities.boot.db.controller;

import by.khodyko.different.securities.boot.db.enums.Role;
import by.khodyko.different.securities.boot.db.model.User;
import by.khodyko.different.securities.boot.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Registration of new User
 */
@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(value = "/user/registration")
    public String createUser(@RequestParam(name = "userName") String userName,
                             @RequestParam(name = "password") String password) {
        User user = new User();
        user.setEnabled(true);
        user.setRole(Role.USER);
        user.setUsername(userName);
        user.setPassword(password);
        userService.createUser(user);
        return "redirect: /login";
    }
}
