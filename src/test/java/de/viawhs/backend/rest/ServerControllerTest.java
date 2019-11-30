package de.viawhs.backend.rest;

import de.viawhs.backend.model.Branch;
import de.viawhs.backend.model.Repository;
import de.viawhs.backend.model.User;
import de.viawhs.backend.service.GitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ServerController.class)
public class ServerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GitService gitService;

    @Test
    public void gitServerTest() throws Exception {
        User user = new User("trnhan251", 1, "github/trnhan251");
        Branch branch1 = new Branch("master");
        Repository[] repos = new Repository[]{
                new Repository(1, "Test", "Test Repository", user, Arrays.asList(branch1))};
        ResponseEntity<Repository[]> response = new ResponseEntity<Repository[]>(HttpStatus.ACCEPTED);
        when(gitService.getAllPublicRepositories("trnhan251"))
                .thenReturn(response);

        RequestBuilder request = MockMvcRequestBuilders
                                        .get("/server/git-repo/public?username=trnhan251")
                                        .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andReturn();
    }
}
