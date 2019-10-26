package de.viawhs.backend.rest;

import de.viawhs.backend.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class ServerController {
    @Autowired GitService gitService;

    @GetMapping("/git-repo")
    public String callGitServer(@RequestParam("username") String username) {
        return gitService.getAllPublicRepositories(username);
    }

}
