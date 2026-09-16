package com.timalsinarajkumar.blog_api.controllers;

import com.timalsinarajkumar.blog_api.dto.post.CreatePostDto;
import com.timalsinarajkumar.blog_api.dto.post.PostResponseDto;
import com.timalsinarajkumar.blog_api.dto.post.UpdatePostDto;
import com.timalsinarajkumar.blog_api.response.ApiResponse;
import com.timalsinarajkumar.blog_api.security.CurrentUserId;
import com.timalsinarajkumar.blog_api.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            @CurrentUserId Long userId,
            @Valid @RequestBody CreatePostDto createPostDto) {

        PostResponseDto postResponseDto =
                postService.createPost(
                        userId,
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
            @CurrentUserId Long userId) {


        List<PostResponseDto> posts =
                postService.getAllPosts(userId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Posts retrieved successfully",
                        posts
                )
        );
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(
            @CurrentUserId Long userId,
            @PathVariable Long postId,
            @Valid @RequestBody UpdatePostDto updatePostDto) {


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
