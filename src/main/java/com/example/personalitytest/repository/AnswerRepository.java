package com.example.personalitytest.repository;


import com.example.personalitytest.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {


}
