package com.example.personalitytest.service;

import com.example.personalitytest.model.Answer;
import com.example.personalitytest.model.User;
import com.example.personalitytest.repository.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserResultServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private UserResultService userResultService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateResultIntrovert() {
        int answersLength = 4;
        String expectedResult = "Introvert";

        String result = userResultService.calculateResult(answersLength);

        assertEquals(expectedResult, result);
    }

    @Test
    void testCalculateResultExtrovert() {
        int answersLength = 5;
        String expectedResult = "Extrovert";

        String result = userResultService.calculateResult(answersLength);

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetUserResult() {
        String userId = "123";

        User user = new User();
        user.setId(Long.valueOf(userId));

        Answer answer1 = new Answer();
        answer1.setUserId(user);
        answer1.setAnswer("answer1");

        Answer answer2 = new Answer();
        answer2.setUserId(user);
        answer2.setAnswer("answer2");
        List<Answer> answers = Arrays.asList(answer1, answer2);
        when(answerRepository.findAll()).thenReturn(answers);

        String result = userResultService.getUserResult(userId);

        assertEquals("Introvert", result);
        verify(answerRepository, times(1)).findAll();
    }
}
