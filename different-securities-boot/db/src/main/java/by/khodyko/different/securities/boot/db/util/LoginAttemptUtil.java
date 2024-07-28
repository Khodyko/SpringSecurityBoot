package by.khodyko.different.securities.boot.db.util;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;

import java.time.LocalDateTime;

/**
 * This utility class is responsible for managing the login attempt logic.
 * It provides methods to check if a login attempt is blocked and if the time since the last attempt has passed.
 */
public class LoginAttemptUtil {
    /**
     * The maximum number of failed login attempts allowed before the account is blocked.
     */
    public static int MAX_ATTEMPT_COUNT=3;
    public static int ATTEMPT_WAITING_TIME_MINUTES=20;

    /**
     * Checks if a login attempt is blocked based on the provided LoginAttempt object.
     *
     * @param loginAttempt the LoginAttempt object containing information about the login attempt
     * @return true if the login attempt is blocked, false otherwise
     */
    public static Boolean isAttemptBlocked(LoginAttempt loginAttempt) {
        if (loginAttempt != null) {
            Boolean isTimeAttemptPassed = isTimePastAfterLastAttempt(loginAttempt);
            boolean isAttemptsAvailable = loginAttempt.getFailedLoginAttempts() < MAX_ATTEMPT_COUNT;
            return !isTimeAttemptPassed && !isAttemptsAvailable;
        } else {
            return true;
        }
    }

    /**
     * Checks if the time since the last failed login attempt has passed.
     *
     * @param loginAttempt the LoginAttempt object containing information about the login attempt
     * @return true if the time since the last attempt has passed, false otherwise
     */
    public static Boolean isTimePastAfterLastAttempt(LoginAttempt loginAttempt){
        LocalDateTime lastAttemptTime = loginAttempt.getLastFailedLoginTime().plusMinutes(ATTEMPT_WAITING_TIME_MINUTES);
       return lastAttemptTime.isBefore(LocalDateTime.now());
    }
}
