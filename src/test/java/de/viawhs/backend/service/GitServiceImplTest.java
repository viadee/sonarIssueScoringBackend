package de.viawhs.backend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GitServiceImplTest {

    static class GitServiceImplTestConfiguration {
        @Bean
        public GitService gitService() {
            return new GitServiceImpl();
        }
    }

    @Autowired
    private GitService gitService;

    @Test
    public void getAllPublicRepositoriesTest() {
        gitService.getAllPublicRepositories("trnhan251");
    }
}
