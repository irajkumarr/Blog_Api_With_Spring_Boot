package com.timalsinarajkumar.blog_api.services;

import com.timalsinarajkumar.blog_api.dto.post.CreatePostDto;
import com.timalsinarajkumar.blog_api.dto.post.PostResponseDto;
import com.timalsinarajkumar.blog_api.dto.post.UpdatePostDto;
import org.springframework.data.domain.Page;

public interface PostService {

    PostResponseDto createPost(Long userId, CreatePostDto createPostDto);

    Page<PostResponseDto> getAllPosts(Long userId, int page, int size, String sortBy);

    PostResponseDto updatePost(Long userId, Long postId, UpdatePostDto updatePostDto);

    void deletePost(Long userId, Long postId);
}
