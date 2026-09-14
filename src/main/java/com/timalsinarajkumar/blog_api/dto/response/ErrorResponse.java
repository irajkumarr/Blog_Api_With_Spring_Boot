package com.timalsinarajkumar.blog_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;
}
