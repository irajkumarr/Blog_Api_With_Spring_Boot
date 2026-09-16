package com.timalsinarajkumar.blog_api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationMeta {
    private int page;
    private int limit;
    private int totalPages;
    private long totalCount;

}

