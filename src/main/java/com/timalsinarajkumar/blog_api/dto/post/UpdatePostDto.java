package com.timalsinarajkumar.blog_api.dto.post;

import lombok.Data;

@Data
public class UpdatePostDto {

    private String title;
    private String content;
    private Boolean published;
}
