package com.example.personalitytest.service;

import com.example.personalitytest.model.Answer;
import com.example.personalitytest.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service that manage to calculate the final result based on user answers
 */
@Service
public class UserResultService {

    private final AnswerRepository answerRepository;

    @Autowired
    public UserResultService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public String getUserResult(String userId) {

        Long userIdAsLong = Long.valueOf(userId);
        int answersLenght = 0;
        List<Answer> allByUserId = answerRepository.findAll().stream().filter(answer -> Objects.equals(answer.getUserId().getId(), userIdAsLong)).collect(Collectors.toList());

        for (Answer answer : allByUserId) {
            answersLenght = answersLenght + answer.getAnswer().length();
        }
        return calculateResult(answersLenght);
    }

    public String calculateResult(int answersLength) {
        return answersLength % 2 == 0 ? "Introvert" : "Extrovert";
    }
}
