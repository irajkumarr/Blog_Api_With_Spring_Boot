package com.timalsinarajkumar.blog_api.services;

import com.timalsinarajkumar.blog_api.dto.auth.UserResponseDto;
import com.timalsinarajkumar.blog_api.dto.user.UpdateUserDto;

import java.util.List;

public interface UserService {

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long id, UpdateUserDto updateUserDto);

    void deleteUser(Long id);
}

