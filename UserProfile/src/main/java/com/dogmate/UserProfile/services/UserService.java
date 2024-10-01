package com.dogmate.UserProfile.services;

import com.dogmate.UserProfile.models.User;
import com.dogmate.UserProfile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(int id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            user.setAge(updatedUser.getAge());
            user.setNickname(updatedUser.getNickname());
            user.setEmail(updatedUser.getEmail());
            user.setSex(updatedUser.isSex());
            user.setDescription(updatedUser.getDescription());
            user.setRole(updatedUser.getRole());
            user.setVerified(updatedUser.isVerified());
            user.setUpdatedAt(updatedUser.getUpdatedAt());
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}