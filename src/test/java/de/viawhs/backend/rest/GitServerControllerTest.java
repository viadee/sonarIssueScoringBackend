package de.viawhs.backend.rest;

import de.viawhs.backend.model.Branch;
import de.viawhs.backend.model.Repository;
import de.viawhs.backend.model.User;
import de.viawhs.backend.service.GitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GitServerController.class)
public class GitServerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private GitService gitService;

    private Repository[] repos;
    public GitServerControllerTest() {
        User user = new User("trnhan251", 1, "github/trnhan251");
        Branch branch = new Branch("master");
        repos = new Repository[]{new Repository(1, "Test", "Test Repository", user, Arrays.asList(branch))};
    }

    @Test
    public void getAllPublicRepositories_Test() throws Exception {
        when(gitService.getAllPublicRepositories("trnhan251"))
                .thenReturn(repos);
        RequestBuilder request = MockMvcRequestBuilders
                                        .get("/server/git-repo/public?username=trnhan251")
                                        .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andReturn();
    }

    @Test
    public void getAllRepositories_Test() throws Exception {
        String token = "12345612121212121";
        when(gitService.getAllRepositories(token)).thenReturn(repos);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/server/git-repo/all")
                .header("token", token)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andExpect(content().json("[" +
                                        "{id: 1, name: Test}" +
                                        "]"))
                                .andReturn();
    }

    @Test
    public void getAllBranchesFromPublicRepos_Test() throws Exception {
        String username = "Test user";
        String repository = "Test Repo";
        Branch[] branches = new Branch[]{ new Branch("master")};
        when(gitService.getAllBranchesFromPublicRepos(username, repository)).thenReturn(branches);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/server/git-repo/public/branches")
                .param("username", username)
                .param("repo", repository)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                                    .andExpect(status().isOk())
                                    .andExpect(content().json("[" +
                                            "{name:master}" +
                                            "]"))
                                    .andReturn();
    }

    @Test
    public void getAllBranchesFromAllRepos_Test() throws Exception {
        String token = "12345612121212121";
        String username = "Test user";
        String repository = "Test Repo";
        Branch[] branches = new Branch[]{ new Branch("master")};
        when(gitService.getAllBranchesFromRepo(token, username, repository)).thenReturn(branches);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/server/git-repo/all/branches")
                .header("token", token)
                .param("username", username)
                .param("repo", repository)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{name:master}" +
                        "]"))
                .andReturn();
    }
}
