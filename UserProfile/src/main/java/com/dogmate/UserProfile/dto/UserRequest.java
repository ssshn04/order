package com.dogmate.UserProfile.dto;

public record UserRequest(
        Long id,
        String name,
        String surname,
        Integer age,
        String nickname,
        String email,
        Boolean sex,
        String description,
        String role,
        Boolean is_verified) {
}
