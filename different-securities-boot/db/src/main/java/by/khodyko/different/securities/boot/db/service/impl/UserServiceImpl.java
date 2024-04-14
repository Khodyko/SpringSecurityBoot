package by.khodyko.different.securities.boot.db.service.impl;

import by.khodyko.different.securities.boot.db.dao.UserRepository;
import by.khodyko.different.securities.boot.db.model.User;
import by.khodyko.different.securities.boot.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userd = userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user by name:" + username));
        return userd;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

}
