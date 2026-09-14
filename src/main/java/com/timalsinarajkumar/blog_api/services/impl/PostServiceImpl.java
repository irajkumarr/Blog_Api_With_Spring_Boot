package com.timalsinarajkumar.blog_api.services.impl;

import com.timalsinarajkumar.blog_api.dto.post.CreatePostDto;
import com.timalsinarajkumar.blog_api.dto.post.PostResponseDto;
import com.timalsinarajkumar.blog_api.dto.post.UpdatePostDto;
import com.timalsinarajkumar.blog_api.entities.Post;
import com.timalsinarajkumar.blog_api.entities.User;
import com.timalsinarajkumar.blog_api.exception.ResourceNotFoundException;
import com.timalsinarajkumar.blog_api.exception.UnauthorizedException;
import com.timalsinarajkumar.blog_api.repositories.PostRepository;
import com.timalsinarajkumar.blog_api.repositories.UserRepository;
import com.timalsinarajkumar.blog_api.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostResponseDto createPost(
            Long userId,
            CreatePostDto createPostDto
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Post post = new Post();

        post.setTitle(createPostDto.getTitle());
        post.setContent(createPostDto.getContent());
        post.setAuthor(user);

        Post savedPost = postRepository.save(post);

        return new PostResponseDto(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.isPublished(),
                savedPost.getAuthor().getId(),
                savedPost.getCreatedAt()
        );
    }

    @Override
    public List<PostResponseDto> getAllPosts(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Post> posts = postRepository.findAllByAuthor(user);

        return posts.stream()
                .map(post -> new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.isPublished(),
                        post.getAuthor().getId(),
                        post.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public PostResponseDto updatePost(
            Long userId,
            Long postId,
            UpdatePostDto updatePostDto
    ) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getAuthor().getId().equals(userId)) {
            throw new UnauthorizedException(
                    "You are not allowed to update this post"
            );
        }

        if (updatePostDto.getTitle() != null) {
            post.setTitle(updatePostDto.getTitle());
        }
        if (updatePostDto.getContent() != null) {
            post.setContent(updatePostDto.getContent());
        }
        if (updatePostDto.getPublished() != null) {
            post.setPublished(updatePostDto.getPublished());
        }

        Post updatedPost = postRepository.save(post);

        return new PostResponseDto(
                updatedPost.getId(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.isPublished(),
                updatedPost.getAuthor().getId(),
                updatedPost.getCreatedAt()
        );
    }

    @Override
    public void deletePost(Long userId, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getAuthor().getId().equals(userId)) {
            throw new UnauthorizedException(
                    "You are not allowed to delete this post"
            );
        }

        postRepository.delete(post);
    }
}