package de.viawhs.backend.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ServerController.class)
public class ServerControllerIT {

    @Autowired
    MockMvc mockMvc;

    /*Testing if Server available*/
    @Test
    public void basicTest() throws Exception {
        this.mockMvc.perform(get("/server"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Server is running")));
    }
}
