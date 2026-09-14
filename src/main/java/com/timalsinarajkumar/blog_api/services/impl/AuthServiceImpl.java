package com.timalsinarajkumar.blog_api.services.impl;

import com.timalsinarajkumar.blog_api.dto.auth.LoginDto;
import com.timalsinarajkumar.blog_api.dto.auth.RegisterDto;
import com.timalsinarajkumar.blog_api.dto.auth.UserResponseDto;
import com.timalsinarajkumar.blog_api.entities.User;
import com.timalsinarajkumar.blog_api.exception.ConflictException;
import com.timalsinarajkumar.blog_api.exception.ResourceNotFoundException;
import com.timalsinarajkumar.blog_api.repositories.UserRepository;
import com.timalsinarajkumar.blog_api.security.JwtService;
import com.timalsinarajkumar.blog_api.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public UserResponseDto register(RegisterDto registerDto) {
        String email = registerDto.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("User with same email already exists in our database");
        }

        User user = new User();

        user.setFullName(registerDto.getFullName());
        user.setEmail(email);
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                null
        );
    }

    @Override
    public UserResponseDto login(LoginDto loginDto) {
        String email = loginDto.getEmail().trim().toLowerCase();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, loginDto
                .getPassword()));

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return new UserResponseDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                token
        );
    }
}
