package com.dogmate.UserProfile.services;

import com.dogmate.UserProfile.dto.UserRequest;
import com.dogmate.UserProfile.models.User;
import com.dogmate.UserProfile.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserRequest userRequest) {
        User user = mapToUser(userRequest);
        return userRepository.save(user);
    }

    public Optional<User> updateUser(int id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateExistingUser(existingUser, userRequest);
                    return userRepository.save(existingUser);
                });
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private static User mapToUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setSurname(userRequest.surname());
        user.setAge(userRequest.age());
        user.setNickname(userRequest.nickname());
        user.setEmail(userRequest.email());
        user.setSex(userRequest.sex());
        user.setDescription(userRequest.description());
        user.setRole(userRequest.role());
        user.setVerified(userRequest.is_verified());
        return user;
    }

    private static void updateExistingUser(User user, UserRequest userRequest) {
        user.setName(userRequest.name());
        user.setSurname(userRequest.surname());
        user.setAge(userRequest.age());
        user.setNickname(userRequest.nickname());
        user.setEmail(userRequest.email());
        user.setSex(userRequest.sex());
        user.setDescription(userRequest.description());
        user.setRole(userRequest.role());
        user.setVerified(userRequest.is_verified());
    }
}
