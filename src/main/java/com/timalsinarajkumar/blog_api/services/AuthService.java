package com.timalsinarajkumar.blog_api.services;

import com.timalsinarajkumar.blog_api.dto.auth.LoginDto;
import com.timalsinarajkumar.blog_api.dto.auth.RegisterDto;
import com.timalsinarajkumar.blog_api.dto.auth.UserResponseDto;

public interface AuthService {

    UserResponseDto register(RegisterDto registerDto);

    UserResponseDto login(LoginDto loginDto);
}
