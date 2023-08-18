package com.example.personalitytest.repository;

import com.example.personalitytest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> getAllByUsername(String username);
}
