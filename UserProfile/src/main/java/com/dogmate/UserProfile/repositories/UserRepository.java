package com.dogmate.UserProfile.repositories;

import com.dogmate.UserProfile.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}