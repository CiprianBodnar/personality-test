package com.example.personalitytest.repository;

import com.example.personalitytest.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
