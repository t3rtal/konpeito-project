package com.tertal.konpeito.service;

import com.tertal.konpeito.dto.UserDto;

public interface UserService {

    void registerUser(UserDto user);

    String verifyUser(UserDto user);

}
