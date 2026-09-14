package com.timalsinarajkumar.blog_api.services;

import com.timalsinarajkumar.blog_api.dto.auth.UserResponse;
import com.timalsinarajkumar.blog_api.dto.user.UpdateUserDto;

public interface UserService {

    UserResponse getUser(Long id);

    UserResponse updateUser(Long id, UpdateUserDto updateUserDto);

    void deleteUser(Long id);
}

