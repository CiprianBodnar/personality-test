package com.example.personalitytest.service;

import com.example.personalitytest.model.User;
import com.example.personalitytest.model.UserResult;
import com.example.personalitytest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that register a user based on the username
 */
@Service
public class UserRegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String username) {

        User user = new User();
        user.setUsername(username);
        user.setResult(String.valueOf(UserResult.NotAssigned));
        userRepository.save(user);
    }
}
