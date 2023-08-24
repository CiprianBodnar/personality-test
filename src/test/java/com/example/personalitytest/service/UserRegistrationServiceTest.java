package com.example.personalitytest.service;

import com.example.personalitytest.model.User;
import com.example.personalitytest.model.UserResult;
import com.example.personalitytest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserRegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        user.setResult(String.valueOf(UserResult.NotAssigned));
        when(userRepository.save(user)).thenReturn(user);

        User registeredUser = userRegistrationService.register(username);

        assertEquals(username, registeredUser.getUsername());
        assertEquals(String.valueOf(UserResult.NotAssigned), registeredUser.getResult());
        verify(userRepository).save(eq(registeredUser));
    }
}
