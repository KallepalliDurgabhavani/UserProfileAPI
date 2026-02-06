package com.example.Userprofileapi.service;  // ← EXACTLY THIS

import com.example.Userprofileapi.model.User;  // ← EXACTLY THIS
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
