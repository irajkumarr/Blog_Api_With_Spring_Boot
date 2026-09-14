package com.timalsinarajkumar.blog_api.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T data;

    private Map<String, String> errors;


    public static <T> ApiResponse<T> success(
            String message,
            T data
    ) {
        return new ApiResponse<>(
                true,
                message,
                data,
                null
        );
    }

    public static <T> ApiResponse<T> error(
            String message
    ) {
        return new ApiResponse<>(
                false,
                message,
                null,
                null
        );
    }

    public static <T> ApiResponse<T> validationError(
            String message,
            Map<String, String> errors
    ) {
        return new ApiResponse<>(
                false,
                message,
                null,
                errors
        );
    }
}
