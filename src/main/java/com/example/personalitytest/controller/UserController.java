package com.example.personalitytest.controller;

import com.example.personalitytest.model.Answer;
import com.example.personalitytest.model.User;
import com.example.personalitytest.repository.AnswerRepository;
import com.example.personalitytest.repository.UserRepository;
import com.example.personalitytest.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRegistrationService userRegistrationService, AnswerRepository answerRepository, UserRepository userRepository) {
        this.userRegistrationService = userRegistrationService;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
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


        Long userIdAsLong = Long.valueOf(String.valueOf(userId));
        int answersLenght = 0;
        User user = userRepository.findById(userIdAsLong).get();
        List<Answer> allByUserId = answerRepository.findAllByUserId(Math.toIntExact(user.getId()));

        for (Answer answer : allByUserId) {
            answersLenght = answersLenght + answer.getAnswer().length();

        }


        return answersLenght % 2 == 0 ? "Introverit" : "Extrovertit";

    }
}
