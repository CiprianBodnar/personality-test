package com.example.personalitytest.controller;

import com.example.personalitytest.model.Answer;
import com.example.personalitytest.model.Question;
import com.example.personalitytest.model.User;
import com.example.personalitytest.repository.AnswerRepository;
import com.example.personalitytest.repository.QuestionRepository;
import com.example.personalitytest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository   questionRepository;

    @Autowired
    public AnswerController(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestParam(value = "questionId") Long questionId, @RequestParam(value = "answer") String answer, @RequestParam(value = "userId") Long userId) {

        if(answer.isEmpty() ) {

            throw new IllegalStateException("Problem with params");
        }

        User user = userRepository.findById(userId).get();
        Question question = questionRepository.findById(questionId).get();

        Answer answer1 = new Answer();
        answer1.setAnswer(answer);
        answer1.setUserId(user);
        answer1.setQuestionId(question);

        answerRepository.save(answer1);




    }
}
