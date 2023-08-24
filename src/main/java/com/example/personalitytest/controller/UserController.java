package com.example.personalitytest.controller;

import com.example.personalitytest.model.User;
import com.example.personalitytest.service.UserRegistrationService;
import com.example.personalitytest.service.UserResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserResultService userResultService;

    @Autowired
    public UserController(UserRegistrationService userRegistrationService, UserResultService userResultService) {
        this.userRegistrationService = userRegistrationService;
        this.userResultService = userResultService;
    }

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@RequestParam(value = "username") String username) {

        if (username.isEmpty()) {
            throw new IllegalStateException("Username should not be blank");
        }
        return userRegistrationService.register(username);
    }

    @GetMapping(path = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public String userResult(@RequestParam(value = "userId") String userId) {

        if (userId.isEmpty()) {
            throw new IllegalArgumentException("User id param is null or empty");
        }
        return userResultService.getUserResult(userId);
    }
}
