package com.alurachallenges.forohub.repository;

import com.alurachallenges.forohub.domain.usuario.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
