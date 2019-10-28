package de.viawhs.backend.rest;

import de.viawhs.backend.model.Repository;
import de.viawhs.backend.model.ServerInfo;
import de.viawhs.backend.service.GitService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/server")
public class ServerController {
    private final GitService gitService;

    @Autowired
    public ServerController(GitService gitService) {
        this.gitService = gitService;
    }

    @GetMapping("/git-repo")
    public Repository[] getAllPublicRepositories(@RequestParam("username") String username) {
        ResponseEntity<Repository[]> response = this.gitService.getAllPublicRepositories(username);
        return response.getBody();
    }

    @PostMapping("/git-repo")
    public Repository[] getAllRepositories(@RequestBody ServerInfo gitServer) {
        ResponseEntity<Repository[]> response = this.gitService.getAllRepositories(gitServer.getToken());
        return response.getBody();
    }
}
