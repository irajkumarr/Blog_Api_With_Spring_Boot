package com.timalsinarajkumar.blog_api.controllers;

import com.timalsinarajkumar.blog_api.dto.common.ApiResponse;
import com.timalsinarajkumar.blog_api.dto.common.PaginationMeta;
import com.timalsinarajkumar.blog_api.dto.post.CreatePostDto;
import com.timalsinarajkumar.blog_api.dto.post.PostResponseDto;
import com.timalsinarajkumar.blog_api.dto.post.UpdatePostDto;
import com.timalsinarajkumar.blog_api.security.CurrentUserId;
import com.timalsinarajkumar.blog_api.services.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "Posts", description = "APIs for managing blog posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(
            @CurrentUserId Long userId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "published", defaultValue = "true") boolean published,
            @RequestParam(value = "imageUrl", required = false) MultipartFile file) {
        CreatePostDto createPostDto = new CreatePostDto(title, content, published);

        PostResponseDto postResponseDto =
                postService.createPost(
                        userId,
                        createPostDto, file
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Post created successfully",
                        postResponseDto
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getAllPosts(
            @CurrentUserId Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {


        Page<PostResponseDto> servicePage =
                postService.getAllPosts(userId, page, size, sortBy);

        PaginationMeta meta = new PaginationMeta(
                servicePage.getNumber(),          // current page
                servicePage.getSize(),            // limit
                servicePage.getTotalPages(),      // total pages
                servicePage.getTotalElements()    // total count
        );


        return ResponseEntity.ok(
                ApiResponse.success(
                        "Posts retrieved successfully",
                        servicePage.getContent(),
                        meta
                )
        );
    }

    @PatchMapping(value = "/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(
            @CurrentUserId Long userId,
            @PathVariable Long postId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "published", defaultValue = "true") boolean published,
            @RequestPart("imageUrl") MultipartFile file) {

        UpdatePostDto updatePostDto = new UpdatePostDto(title, content, published);

        PostResponseDto updatedPost =
                postService.updatePost(
                        userId,
                        postId,
                        updatePostDto
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Post updated successfully",
                        updatedPost
                )
        );
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<String>> deletePost(
            @CurrentUserId Long userId,
            @PathVariable Long postId) {

        postService.deletePost(
                userId,
                postId
        );

        return ResponseEntity.noContent().build();
    }
}
