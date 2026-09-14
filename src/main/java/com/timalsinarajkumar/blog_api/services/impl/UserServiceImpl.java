package com.timalsinarajkumar.blog_api.services.impl;

import com.timalsinarajkumar.blog_api.dto.auth.UserResponseDto;
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
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new UserResponseDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                null
        );
    }

    @Override
    public UserResponseDto updateUser(Long id, UpdateUserDto updateUserDto) {
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
        return new UserResponseDto(
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
