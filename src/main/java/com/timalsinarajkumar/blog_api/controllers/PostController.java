package com.timalsinarajkumar.blog_api.controllers;

import com.timalsinarajkumar.blog_api.dto.post.CreatePostDto;
import com.timalsinarajkumar.blog_api.dto.post.PostResponseDto;
import com.timalsinarajkumar.blog_api.dto.post.UpdatePostDto;
import com.timalsinarajkumar.blog_api.response.ApiResponse;
import com.timalsinarajkumar.blog_api.security.CustomUserDetails;
import com.timalsinarajkumar.blog_api.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(
            Authentication authentication,
            @Valid @RequestBody CreatePostDto createPostDto) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        PostResponseDto postResponseDto =
                postService.createPost(
                        userDetails.getUserId(),
                        createPostDto
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
            Authentication authentication) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        List<PostResponseDto> posts =
                postService.getAllPosts(userDetails.getUserId());

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Posts retrieved successfully",
                        posts
                )
        );
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(
            Authentication authentication,
            @PathVariable Long postId,
            @Valid @RequestBody UpdatePostDto updatePostDto) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        PostResponseDto updatedPost =
                postService.updatePost(
                        userDetails.getUserId(),
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
            Authentication authentication,
            @PathVariable Long postId) {

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        postService.deletePost(
                userDetails.getUserId(),
                postId
        );

        return ResponseEntity.noContent().build();
    }
}
