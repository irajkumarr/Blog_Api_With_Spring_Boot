package com.timalsinarajkumar.blog_api.repositories;

import com.timalsinarajkumar.blog_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);

    public boolean existsByPhone(String phone);
}
