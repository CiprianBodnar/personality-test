package com.example.personalitytest.controller;

import com.example.personalitytest.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AnswerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private AnswerController answerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(answerController).build();
    }

    @Test
    void testRegisterUserValidAnswer() throws Exception {
        Long questionId = 1L;
        String answer = "Some answer";
        Long userId = 123L;

        mockMvc.perform(post("/api/answers/create")
                        .param("questionId", questionId.toString())
                        .param("answer", answer)
                        .param("userId", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(answerService).saveAnswer(userId, questionId, answer);
    }
}
