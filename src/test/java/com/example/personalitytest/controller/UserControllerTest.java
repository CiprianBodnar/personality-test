package com.example.personalitytest.controller;

import com.example.personalitytest.model.User;
import com.example.personalitytest.service.UserRegistrationService;
import com.example.personalitytest.service.UserResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRegistrationService userRegistrationService;

    @Mock
    private UserResultService userResultService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testRegisterUserValidUsername() throws Exception {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        when(userRegistrationService.register(username)).thenReturn(user);

        mockMvc.perform(post("/api/users/register")
                        .param("username", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userRegistrationService).register(username);
    }

    @Test
    void testUserResult() throws Exception {
        String userId = "123";
        String result = "Extrovert";
        when(userResultService.getUserResult(userId)).thenReturn(result);

        mockMvc.perform(get("/api/users/result")
                        .param("userId", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userResultService).getUserResult(userId);
    }
}
