package by.khodyko.different.securities.boot.db.service;

import by.khodyko.different.securities.boot.db.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(User user);
}
