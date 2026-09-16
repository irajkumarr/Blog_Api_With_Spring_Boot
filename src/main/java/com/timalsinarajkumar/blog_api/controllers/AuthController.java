package com.timalsinarajkumar.blog_api.controllers;

import com.timalsinarajkumar.blog_api.dto.auth.LoginDto;
import com.timalsinarajkumar.blog_api.dto.auth.RegisterDto;
import com.timalsinarajkumar.blog_api.dto.auth.UserResponseDto;
import com.timalsinarajkumar.blog_api.response.ApiResponse;
import com.timalsinarajkumar.blog_api.services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "APIs for authentication and user registration")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    @SecurityRequirements
    public ResponseEntity<ApiResponse<UserResponseDto>> register(@Valid @RequestBody RegisterDto registerDto) {
        UserResponseDto response = authService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Registered successfully", response));

    }

    @PostMapping("/login")
    @SecurityRequirements
    public ResponseEntity<ApiResponse<UserResponseDto>> login(@Valid @RequestBody LoginDto loginDto) {
        UserResponseDto response = authService.login(loginDto);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }
}
