package by.khodyko.different.securities.boot.db.service;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;
import by.khodyko.different.securities.boot.db.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
/**
 * This interface extends the UserDetailsService interface and provides additional methods
 * for managing user information, including login attempts.
 */
public interface UserService extends UserDetailsService {

    /**
     * Retrieves the user information for the specified username.
     *
     * @param userName the username of the user
     * @return an Optional containing the user information, or an empty Optional if the user is not found
     */
    Optional<User> getUserByUserName(String userName);


    /**
     * Resets the login attempt count to zero for the specified user.
     *
     * @param userName the username of the user
     */
    void setLoginAttemptsZero(String userName);

    /**
     * Increases the login attempt count for the specified user.
     *
     * @param userName the username of the user
     */
    void increaseLoginAttempts(String userName);

    /**
     * Creates a new user with the provided user information.
     *
     * @param user the user information to be used for creating the new user
     * @return the created user
     */
    User createUser(User user);

}
