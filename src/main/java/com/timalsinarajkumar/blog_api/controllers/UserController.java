package com.timalsinarajkumar.blog_api.controllers;

import com.timalsinarajkumar.blog_api.dto.auth.UserResponse;
import com.timalsinarajkumar.blog_api.dto.user.UpdateUserDto;
import com.timalsinarajkumar.blog_api.response.ApiResponse;
import com.timalsinarajkumar.blog_api.security.CustomUserDetails;
import com.timalsinarajkumar.blog_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(ApiResponse.success("User fetched successfully", userService.getUser(userDetails.getUserId())));
    }

    @PatchMapping()
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(Authentication authentication, @Valid @RequestBody UpdateUserDto updateUserDto) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", userService.updateUser(userDetails.getUserId(), updateUserDto)));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        userService.deleteUser(userDetails.getUserId());
        return ResponseEntity.noContent().build();
    }


}
