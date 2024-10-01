package com.dogmate.UserProfile;

import com.dogmate.UserProfile.models.User;
import com.dogmate.UserProfile.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();

        testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Doe");
        testUser.setAge(30);
        testUser.setNickname("johndoe");
        testUser.setEmail("john.doe@example.com");
        testUser.setSex(true);
        testUser.setDescription("Test user");
        testUser.setRole("Owner");
        testUser.setVerified(true);
        testUser.setCreatedAt(LocalDateTime.now());
        testUser.setUpdatedAt(LocalDateTime.now());

        testUser = userRepository.save(testUser);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(testUser.getName())))
                .andExpect(jsonPath("$[0].email", is(testUser.getEmail())));
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(get("/users/{id}", testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testUser.getId())))
                .andExpect(jsonPath("$.name", is(testUser.getName())))
                .andExpect(jsonPath("$.email", is(testUser.getEmail())));
    }

    @Test
    public void testGetUserById_NotFound() throws Exception {
        mockMvc.perform(get("/users/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUser() throws Exception {
        User newUser = new User();
        newUser.setName("Jane");
        newUser.setSurname("Smith");
        newUser.setAge(25);
        newUser.setNickname("janesmith");
        newUser.setEmail("jane.smith@example.com");
        newUser.setSex(false);
        newUser.setDescription("Another test user");
        newUser.setRole("Professional");
        newUser.setVerified(false);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(newUser.getName())))
                .andExpect(jsonPath("$.email", is(newUser.getEmail())));
    }

    @Test
    public void testUpdateUser() throws Exception {
        testUser.setName("John Updated");
        testUser.setEmail("john.updated@example.com");

        mockMvc.perform(put("/users/{id}", testUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testUser.getId())))
                .andExpect(jsonPath("$.name", is("John Updated")))
                .andExpect(jsonPath("$.email", is("john.updated@example.com")));
    }

    @Test
    public void testUpdateUser_NotFound() throws Exception {
        User updatedUser = new User();
        updatedUser.setName("Nonexistent");
        updatedUser.setSurname("User");
        updatedUser.setAge(40);
        updatedUser.setNickname("nonexistent");
        updatedUser.setEmail("nonexistent@example.com");
        updatedUser.setSex(true);
        updatedUser.setDescription("Does not exist");
        updatedUser.setRole("Owner");
        updatedUser.setVerified(false);
        updatedUser.setCreatedAt(LocalDateTime.now());
        updatedUser.setUpdatedAt(LocalDateTime.now());

        mockMvc.perform(put("/users/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/{id}", testUser.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/users/{id}", testUser.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUser_NotFound() throws Exception {
        mockMvc.perform(delete("/users/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
