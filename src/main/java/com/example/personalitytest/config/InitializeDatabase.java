package com.example.personalitytest.config;

import com.example.personalitytest.model.Question;
import com.example.personalitytest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitializeDatabase {

    private final QuestionRepository questionRepository;

    @Autowired
    public InitializeDatabase(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostConstruct
    public void createQuestions() {

        questionRepository.deleteAll();

        List<Question> questionList = new ArrayList<>();
        questionList.add(createQuestion("You’re really busy at work and a colleague is telling you their life story and personal woes. You:",
                 new ArrayList<>(Arrays.asList("Don’t dare to interrupt them",
                        "Think it’s more important to give them some of your time; work can wait",
                        "Listen, but with only with half an ear",
                        "Interrupt and explain that you are really busy at the moment"))));

        questionList.add(createQuestion("You’ve been sitting in the doctor’s waiting room for more than 25 minutes. You:",
                new ArrayList<>(Arrays.asList("Don’t dare to interrupt them",
                "Think it’s more important to give them some of your time; work can wait",
                "Listen, but with only with half an ear",
                "Interrupt and explain that you are really busy at the moment"))));

        questionList.add(createQuestion("You’re having an animated discussion with a colleague regarding a project that you’re in charge of. You:",
                new ArrayList<>(Arrays.asList("Don’t dare to interrupt them",
                "Think it’s more important to give them some of your time; work can wait",
                "Listen, but with only with half an ear",
                "Interrupt and explain that you are really busy at the moment"))));


        questionRepository.saveAll(questionList);
    }

    private static Question createQuestion(String questionString, List<String> options) {
        Question question = new Question();
        question.setQuestion(questionString);
        question.setOptions(options);
        return question;
    }
}
