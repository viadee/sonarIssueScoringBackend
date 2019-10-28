package de.viawhs.backend.rest;

import de.viawhs.backend.service.GitService;
import de.viawhs.backend.service.GitServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ServerController.class)
public class ServerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GitService gitService;

    @Test
    public void gitServerTest() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/server/git-repo?username=trnhan251")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
