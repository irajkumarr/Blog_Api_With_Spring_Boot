package com.timalsinarajkumar.blog_api.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePostDto {

    private String title;
    private String content;
    private Boolean published;
}
