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
                new ArrayList<>(Arrays.asList("Look at your watch every two minutes",
                        "Bubble with inner anger, but keep quiet",
                        "Explain to other equally impatient people in the room that the doctor is always running late",
                        "Complain in a loud voice, while tapping your foot impatiently"))));

        questionList.add(createQuestion("You’re having an animated discussion with a colleague regarding a project that you’re in charge of. You:",
                new ArrayList<>(Arrays.asList("Don’t dare contradict them",
                        "Think that they are obviously right",
                        "Defend your own point of view, tooth and nail",
                        "Continuously interrupt your colleague"))));

        questionList.add(createQuestion("During dinner parties at your home, you have a hard time with people who:",
                new ArrayList<>(Arrays.asList("Ask you to tell a story in front of everyone else",
                        "Talk privately between themselves",
                        "Hang around you all evening",
                        "Always drag the conversation back to themselves"))));

        questionList.add(createQuestion("You crack a joke at work, but nobody seems to have noticed. You:",
                new ArrayList<>(Arrays.asList("Think it’s for the best — it was a lame joke anyway",
                        "Wait to share it with your friends after work",
                        "Try again a bit later with one of your colleagues",
                        "Keep telling it until they pay attention"))));

        questionRepository.saveAll(questionList);
    }

    private static Question createQuestion(String questionString, List<String> options) {
        Question question = new Question();
        question.setQuestion(questionString);
        question.setOptions(options);
        return question;
    }
}
