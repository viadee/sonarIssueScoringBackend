package de.viawhs.backend.service;

import de.viawhs.backend.model.Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GitServiceTest {

    @InjectMocks
    private GitService gitService = new GitService();

    @Test
    public void testGetAllPublicRepos() {
        Repository[] repos = gitService.getAllPublicRepositories("trnhan251");
        assertEquals(repos[0].getId(), 191923132);
    }

    @Test
    public void testGetAllRepos() {
        Repository[] repos = gitService.getAllRepositories("7a11ea674bcd7d473895ed8c9d492ecac1891938");
        assertEquals(repos[0].getId(), 222391923);
    }
}
