package com.timalsinarajkumar.blog_api.services.impl;

import com.timalsinarajkumar.blog_api.dto.auth.UserResponse;
import com.timalsinarajkumar.blog_api.dto.user.UpdateUserDto;
import com.timalsinarajkumar.blog_api.entities.User;
import com.timalsinarajkumar.blog_api.exception.ResourceNotFoundException;
import com.timalsinarajkumar.blog_api.repositories.UserRepository;
import com.timalsinarajkumar.blog_api.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                null
        );
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (updateUserDto.getEmail() != null) {
            user.setEmail(updateUserDto.getEmail());
        }
        if (updateUserDto.getFullName() != null) {
            user.setFullName(updateUserDto.getFullName());
        }
        if (updateUserDto.getPhone() != null) {
            user.setPhone(updateUserDto.getPhone());
        }

        User updatedUser = userRepository.save(user);
        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getFullName(),
                updatedUser.getEmail(),
                updatedUser.getPhone(),
                null
        );
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
