package by.khodyko.different.securities.boot.db.dao;

import by.khodyko.different.securities.boot.db.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * For work with LoginAttempts Entity
 *
 * @see LoginAttempt
 */
@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

    /**
     * Returns loginAttempt Entity For User with userName
     * @param userName
     * @return
     */
    @Query(value = "SELECT u.loginAttempt FROM User u WHERE u.username =?1")
    LoginAttempt getLoginAttemptByUserName(String userName);
}
