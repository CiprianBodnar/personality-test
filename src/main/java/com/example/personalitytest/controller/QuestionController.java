package com.example.personalitytest.controller;

import com.example.personalitytest.model.Question;
import com.example.personalitytest.model.dto.QuestionDto;
import com.example.personalitytest.repository.QuestionRepository;
import com.example.personalitytest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestionDto> getAllQuestions() {

        return questionService.getAllQuestions();
    }

}
