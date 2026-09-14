package com.timalsinarajkumar.blog_api.services.impl;

import com.timalsinarajkumar.blog_api.dto.auth.LoginDto;
import com.timalsinarajkumar.blog_api.dto.auth.RegisterDto;
import com.timalsinarajkumar.blog_api.dto.auth.UserResponse;
import com.timalsinarajkumar.blog_api.entities.User;
import com.timalsinarajkumar.blog_api.exception.ConflictException;
import com.timalsinarajkumar.blog_api.repositories.UserRepository;
import com.timalsinarajkumar.blog_api.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserResponse register(RegisterDto registerDto) {
        String email = registerDto.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("User with same email already exists in our database");
        }

        User user=new User();

        user.setFullName(registerDto.getFullName());
        user.setEmail(email);
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

       User savedUser= userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                null
        );
    }

    @Override
    public UserResponse login(LoginDto loginDto) {
        return null;
    }
}
