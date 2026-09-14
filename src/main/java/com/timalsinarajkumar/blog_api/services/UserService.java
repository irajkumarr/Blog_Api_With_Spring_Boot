package com.timalsinarajkumar.blog_api.services;

import com.timalsinarajkumar.blog_api.dto.auth.UserResponseDto;
import com.timalsinarajkumar.blog_api.dto.user.UpdateUserDto;

public interface UserService {

    UserResponseDto getUser(Long id);

    UserResponseDto updateUser(Long id, UpdateUserDto updateUserDto);

    void deleteUser(Long id);
}

