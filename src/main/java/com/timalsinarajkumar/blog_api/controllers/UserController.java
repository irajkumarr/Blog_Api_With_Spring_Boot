package com.timalsinarajkumar.blog_api.controllers;

import com.timalsinarajkumar.blog_api.dto.auth.UserResponseDto;
import com.timalsinarajkumar.blog_api.dto.user.UpdateUserDto;
import com.timalsinarajkumar.blog_api.response.ApiResponse;
import com.timalsinarajkumar.blog_api.security.CurrentUserId;
import com.timalsinarajkumar.blog_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@CurrentUserId Long userId) {
        return ResponseEntity.ok(ApiResponse.success("User fetched successfully", userService.getUser(userId)));
    }

    @PatchMapping()
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@CurrentUserId Long userId, @Valid @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", userService.updateUser(userId, updateUserDto)));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@CurrentUserId Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


}
