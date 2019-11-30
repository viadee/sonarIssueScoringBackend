package de.viawhs.backend.rest;

import de.viawhs.backend.model.Branch;
import de.viawhs.backend.model.Repository;
import de.viawhs.backend.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class ServerController {
    private GitService gitService;

    @Autowired
    public ServerController(GitService gitService) {
        this.gitService = gitService;
    }

    // Get all public repositories with a username
    @GetMapping("/git-repo/public")
    public Repository[] getAllPublicRepositories(@RequestParam("username") String username) {
        ResponseEntity<Repository[]> response = this.gitService.getAllPublicRepositories(username);
        return response.getBody();
    }

    // Get all public and private repositories with a token
    @GetMapping("/git-repo/all")
    public Repository[] getAllRepositories(@RequestHeader("token") String token) {
        ResponseEntity<Repository[]> response = this.gitService.getAllRepositories(token);
        return response.getBody();
    }

    @GetMapping("/git-repo/public/branches")
    public Branch[] getAllBranchesFromPublicRepos(@RequestParam("username") String username, @RequestParam("repo") String repo) {
        ResponseEntity<Branch[]> response = this.gitService.getAllBranchesFromPublicRepos(username, repo);
        return response.getBody();
    }

    @GetMapping("/git-repo/all/branches")
    public Branch[] getAllBranchesFromAllRepos(@RequestHeader("token") String token, @RequestParam("username") String username, @RequestParam("repo") String repo) {
        ResponseEntity<Branch[]> response = this.gitService.getAllBranchesFromRepo(token, username, repo);
        return response.getBody();
    }
}
