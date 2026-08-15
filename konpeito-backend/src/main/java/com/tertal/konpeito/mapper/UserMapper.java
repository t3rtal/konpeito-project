package com.tertal.konpeito.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tertal.konpeito.dto.UserDto;
import com.tertal.konpeito.entity.User;

public class UserMapper {

    private static final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder(12);

    public static User mapToUser(UserDto user) {
        return User.builder()
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .build();
    }

}
