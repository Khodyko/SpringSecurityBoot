package by.khodyko.different.securities.boot.db.service;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;

/**
 * This interface defines the contract for a service that manages login attempts.
 * It provides methods to save a login attempt and retrieve the login attempt information for a specific user.
 */
public interface LoginAttemptService {

    /**
     * Saves a login attempt.
     *
     * @param loginAttempt the login attempt to be saved
     * @return the saved login attempt
     */
    public LoginAttempt save(LoginAttempt loginAttempt);

    /**
     * Retrieves the login attempt information for the specified user.
     *
     * @param userName the username of the user
     * @return the login attempt for the specified user
     */
    public LoginAttempt getLoginAttemptByUserName(String userName);
}
