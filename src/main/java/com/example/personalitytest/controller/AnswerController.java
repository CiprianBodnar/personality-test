package com.example.personalitytest.controller;

import com.example.personalitytest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestParam(value = "questionId") Long questionId, @RequestParam(value = "answer") String answer, @RequestParam(value = "userId") Long userId) {

        if (answer.isEmpty()) {
            throw new IllegalStateException("Answer is null or empty");
        }

        answerService.saveAnswer(userId, questionId, answer);
    }
}
