package com.timalsinarajkumar.blog_api.services;

import com.timalsinarajkumar.blog_api.dto.post.CreatePostDto;
import com.timalsinarajkumar.blog_api.dto.post.PostResponseDto;
import com.timalsinarajkumar.blog_api.dto.post.UpdatePostDto;

import java.util.List;

public interface PostService {

    PostResponseDto createPost(Long userId, CreatePostDto createPostDto);

    List<PostResponseDto> getAllPosts(Long userId);

    PostResponseDto updatePost(Long userId, Long postId, UpdatePostDto updatePostDto);

    void deletePost(Long userId, Long postId);
}
