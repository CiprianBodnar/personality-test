package com.example.personalitytest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser() {


    }
}
