package com.example.lab1.repository.Security;

import com.example.lab1.model.Security.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}