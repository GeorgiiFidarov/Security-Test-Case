package ru.fidarov.Security.Test.Case;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddUser() throws Exception {
        String requestBody = "{\"username\":\"testUser\",\"passwordOrHash\":\"testPassword\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAuthenticate() throws Exception {
        String requestBody = "{\"username\":\"testUser\",\"passwordOrHash\":\"testPassword\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Да"));
    }
}
