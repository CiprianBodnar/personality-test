package com.example.personalitytest.service;

import com.example.personalitytest.model.Answer;
import com.example.personalitytest.model.Question;
import com.example.personalitytest.model.User;
import com.example.personalitytest.repository.AnswerRepository;
import com.example.personalitytest.repository.QuestionRepository;
import com.example.personalitytest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Answer saveAnswer(Long userId, Long questionId, String answerString) {

        User user = userRepository.findById(userId).get();
        Question question = questionRepository.findById(questionId).get();

        Answer answer = new Answer();
        answer.setAnswer(answerString);
        answer.setUserId(user);
        answer.setQuestionId(question);

        return answerRepository.save(answer);
    }
}
