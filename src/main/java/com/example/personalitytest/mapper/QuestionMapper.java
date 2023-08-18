package com.example.personalitytest.mapper;

import com.example.personalitytest.model.Question;
import com.example.personalitytest.model.dto.QuestionDto;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    public QuestionDto toDto(Question question) {

        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestion(question.getQuestion());
        questionDto.setOptions(question.getOptions());
        return new QuestionDto();
    }

    public Question toModel() {

       return new Question();
    }
}
