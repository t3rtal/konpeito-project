package com.tertal.konpeito;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tertal.konpeito.repository.ApplicationRepository;
import com.tertal.konpeito.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationAndDataIsolationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clearDatabase() {
        this.applicationRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    void unauthenticatedRequestsAreRejected() throws Exception {
        this.mockMvc.perform(get("/api/applications"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void usersCanRegisterLoginAndOnlySeeTheirOwnApplications() throws Exception {
        register("alice", "alice-password");
        register("bob", "bob-password");

        String aliceToken = login("alice", "alice-password");
        String bobToken = login("bob", "bob-password");

        MvcResult created = this.mockMvc.perform(post("/api/applications")
                        .header("Authorization", "Bearer " + aliceToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "company": "Alice Company",
                                  "position": "Engineer",
                                  "status": "APPLIED",
                                  "salary": 100000
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.company").value("Alice Company"))
                .andReturn();

        long applicationId = this.objectMapper.readTree(
                created.getResponse().getContentAsString()).get("id").asLong();

        this.mockMvc.perform(get("/api/applications")
                        .header("Authorization", "Bearer " + aliceToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        this.mockMvc.perform(get("/api/applications")
                        .header("Authorization", "Bearer " + bobToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        this.mockMvc.perform(get("/api/applications/" + applicationId)
                        .header("Authorization", "Bearer " + bobToken))
                .andExpect(status().isNotFound());
    }

    private void register(String username, String password) throws Exception {
        this.mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"username":"%s","password":"%s"}
                                """.formatted(username, password)))
                .andExpect(status().isOk());
    }

    private String login(String username, String password) throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"username":"%s","password":"%s"}
                                """.formatted(username, password)))
                .andExpect(status().isOk())
                .andReturn();

        return result.getResponse().getContentAsString().trim();
    }
}
