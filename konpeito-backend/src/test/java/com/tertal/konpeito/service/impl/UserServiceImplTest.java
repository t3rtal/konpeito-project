package com.tertal.konpeito.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tertal.konpeito.dto.UserDto;
import com.tertal.konpeito.entity.User;
import com.tertal.konpeito.repository.UserRepository;
import com.tertal.konpeito.security.JwtUtils;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void registerUserHashesPasswordBeforeSaving() {
        UserDto user = user("alice", "plain-password");
        when(this.userRepository.existsByUsername("alice")).thenReturn(false);

        this.service.registerUser(user);

        ArgumentCaptor<User> savedUser = ArgumentCaptor.forClass(User.class);
        verify(this.userRepository).save(savedUser.capture());
        assertNotEquals("plain-password", savedUser.getValue().getPassword());
        org.junit.jupiter.api.Assertions.assertTrue(
                new BCryptPasswordEncoder().matches(
                        "plain-password", savedUser.getValue().getPassword()));
    }

    @Test
    void registerUserRejectsDuplicateUsername() {
        UserDto user = user("alice", "password");
        when(this.userRepository.existsByUsername("alice")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> this.service.registerUser(user));
        verify(this.userRepository, never()).save(any(User.class));
    }

    @Test
    void verifyUserReturnsTokenForAuthenticatedUser() {
        UserDto user = user("alice", "password");
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "alice", null, AuthorityUtils.NO_AUTHORITIES);
        when(this.authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(this.jwtUtils.generateToken("alice")).thenReturn("jwt-token");

        org.junit.jupiter.api.Assertions.assertEquals(
                "jwt-token", this.service.verifyUser(user));
        verify(this.jwtUtils).generateToken("alice");
    }

    @Test
    void verifyUserPropagatesAuthenticationFailure() {
        UserDto user = user("alice", "wrong");
        when(this.authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new org.springframework.security.core.AuthenticationException("bad credentials") {});

        assertThrows(org.springframework.security.core.AuthenticationException.class,
                () -> this.service.verifyUser(user));
    }

    private UserDto user(String username, String password) {
        UserDto user = new UserDto();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
