package com.timalsinarajkumar.blog_api.dto.user;

import lombok.Data;

@Data
public class UpdateUserDto {


    private String email;

    private String fullName;

    private String phone;
}
