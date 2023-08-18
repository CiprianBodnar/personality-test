package com.example.personalitytest.service;

import com.example.personalitytest.mapper.QuestionMapper;
import com.example.personalitytest.model.Question;
import com.example.personalitytest.model.dto.QuestionDto;
import com.example.personalitytest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public List<QuestionDto> getAllQuestions() {

        List<QuestionDto> questionDtos = new ArrayList<>();
        List<Question> all = questionRepository.findAll();
        for (Question question: all) {

            QuestionDto questionDto = questionMapper.toDto(question);
            questionDtos.add(questionDto);
        }

        return questionDtos;
    }
}
