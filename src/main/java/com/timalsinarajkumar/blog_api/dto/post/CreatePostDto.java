package com.timalsinarajkumar.blog_api.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePostDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    private boolean published;
}
