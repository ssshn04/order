package com.dogmate.UserProfile;

import com.dogmate.UserProfile.models.User;
import com.dogmate.UserProfile.repositories.UserRepository;
import com.dogmate.UserProfile.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setAge(30);
        user.setNickname("johnny");
        user.setEmail("john@example.com");
        user.setSex(true);
        user.setDescription("Test user");
        user.setRole("Owner");
        user.setVerified(true);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setName("Jane");
        user.setSurname("Doe");
        user.setAge(28);
        user.setNickname("jane");
        user.setEmail("jane@example.com");
        user.setSex(false);
        user.setDescription("Another test user");
        user.setRole("Professional");
        user.setVerified(true);
        user = userRepository.save(user);

        mockMvc.perform(get("/api/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane"))
                .andExpect(jsonPath("$.surname").value("Doe"));
    }

    @Test
    void testDeleteUser() throws Exception {
        User user = new User();
        user.setName("Mark");
        user.setSurname("Smith");
        user.setAge(35);
        user.setNickname("marky");
        user.setEmail("mark@example.com");
        user.setSex(true);
        user.setDescription("User to be deleted");
        user.setRole("Owner");
        user.setVerified(true);
        user = userRepository.save(user);

        mockMvc.perform(delete("/api/users/" + user.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/users/" + user.getId()))
                .andExpect(status().isNotFound());
    }
}
