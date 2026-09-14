package com.timalsinarajkumar.blog_api.services;

import com.timalsinarajkumar.blog_api.dto.auth.LoginDto;
import com.timalsinarajkumar.blog_api.dto.auth.RegisterDto;
import com.timalsinarajkumar.blog_api.dto.auth.UserResponse;

public interface AuthService {

    UserResponse register(RegisterDto registerDto);
    UserResponse login(LoginDto loginDto);
}
