package com.timalsinarajkumar.blog_api.repositories;

import com.timalsinarajkumar.blog_api.entities.Post;
import com.timalsinarajkumar.blog_api.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findAllByAuthor(User author);

    Page<Post> findAllByAuthor(User author, Pageable pageable);
}
