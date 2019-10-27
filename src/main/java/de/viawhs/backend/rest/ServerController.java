package de.viawhs.backend.rest;

import de.viawhs.backend.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class ServerController {
    private final GitService gitService;

    @Autowired
    public ServerController(GitService gitService) {
        this.gitService = gitService;
    }

    @GetMapping("/git-repo")
    public String callGitServer(@RequestParam("username") String username) {
        return gitService.getAllPublicRepositories(username);
    }


}
