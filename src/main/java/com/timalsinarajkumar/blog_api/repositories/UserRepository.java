package com.timalsinarajkumar.blog_api.repositories;

import com.timalsinarajkumar.blog_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
