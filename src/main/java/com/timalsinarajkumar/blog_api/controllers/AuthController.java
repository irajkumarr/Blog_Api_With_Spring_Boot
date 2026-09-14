package com.timalsinarajkumar.blog_api.controllers;

import com.timalsinarajkumar.blog_api.dto.auth.LoginDto;
import com.timalsinarajkumar.blog_api.dto.auth.RegisterDto;
import com.timalsinarajkumar.blog_api.dto.auth.UserResponse;
import com.timalsinarajkumar.blog_api.response.ApiResponse;
import com.timalsinarajkumar.blog_api.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterDto registerDto) {
        UserResponse response = authService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Registered successfully", response));

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@Valid @RequestBody LoginDto loginDto) {
        UserResponse response = authService.login(loginDto);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }
}
