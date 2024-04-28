package by.khodyko.different.securities.boot.db.controller;

import by.khodyko.different.securities.boot.db.enums.Role;
import by.khodyko.different.securities.boot.db.model.User;
import by.khodyko.different.securities.boot.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping
    @RequestMapping(value = "/user/registration")
    public String createUser(@RequestParam String userName, @RequestParam String password){
        User user=new User();
        user.setEnabled(true);
        user.setRole(Role.USER);
        user.setUsername(userName);
        user.setPassword(password);
        userService.createUser(user);
        return "redirect: /login";
    }
}
