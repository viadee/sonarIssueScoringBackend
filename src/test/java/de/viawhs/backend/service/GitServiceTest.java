package de.viawhs.backend.service;

import de.viawhs.backend.model.Branch;
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
    public void getAllPublicRepositories_Test() {
        Repository[] repos = gitService.getAllPublicRepositories("trnhan251");
        assertEquals(repos[0].getId(), 191923132);
    }

    @Test
    public void getAllRepositories_Test() {
        Repository[] repos = gitService.getAllRepositories("fe08bf9d34635c26e308882db8f6e2930fc687b6");
        assertEquals(repos[0].getId(), 222391923);
    }

    @Test
    public void getAllBranchesFromPublicRepos_Test() {
        Branch[] branches = gitService.getAllBranchesFromPublicRepos("trnhan251", "AgilePPMTool");
        assertEquals(branches.length, 1);
    }

    @Test
    public void getAllBranchesFromRepo_Test() {
        Branch[] branches = gitService.getAllBranchesFromRepo("fe08bf9d34635c26e308882db8f6e2930fc687b6", "trnhan251", "AgilePPMTool");
        assertEquals(branches.length, 1);
    }
}
