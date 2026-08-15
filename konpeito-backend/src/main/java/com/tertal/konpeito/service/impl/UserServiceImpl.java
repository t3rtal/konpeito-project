package com.tertal.konpeito.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tertal.konpeito.dto.UserDto;
import com.tertal.konpeito.entity.User;
import com.tertal.konpeito.mapper.UserMapper;
import com.tertal.konpeito.repository.UserRepository;
import com.tertal.konpeito.security.JwtUtils;
import com.tertal.konpeito.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserDto user) {
        if (this.userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Invalid Username");
        }

        User newUser = UserMapper.mapToUser(user);
        this.userRepository.save(newUser);
    }

    @Override
    public String verifyUser(UserDto user) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            return this.jwtUtils.generateToken(user.getUsername());
        }

        return "Failed login";
    }

}
