package com.timalsinarajkumar.blog_api.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private boolean published;
    private Long authorId;
    private LocalDateTime createdAt;

}

