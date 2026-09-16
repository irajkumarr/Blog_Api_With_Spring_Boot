package com.timalsinarajkumar.blog_api.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private PaginationMeta meta;
    private Map<String, String> errors;

    // 1. Standard Success Factory (For regular objects/lists without pagination)
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(
                true,
                message,
                data,
                null, // No metadata needed for standard requests
                null
        );
    }

    // 2. Paginated Success Factory (Accepts data and meta separately)
    public static <T> ApiResponse<T> success(String message, T data, PaginationMeta meta) {
        return new ApiResponse<>(
                true,
                message,
                data,
                meta,
                null
        );
    }

    // 3. Error Factory
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(
                false,
                message,
                null,
                null,
                null
        );
    }

    // 4. Validation Error Factory
    public static <T> ApiResponse<T> validationError(String message, Map<String, String> errors) {
        return new ApiResponse<>(
                false,
                message,
                null,
                null,
                errors
        );
    }
}
