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
        Repository[] results = this.gitService.getAllPublicRepositories(username);
        return results;
    }

    // Get all public and private repositories with a token
    @GetMapping("/git-repo/all")
    public Repository[] getAllRepositories(@RequestHeader("token") String token) {
        Repository[] results = this.gitService.getAllRepositories(token);
        return results;
    }

    @GetMapping("/git-repo/public/branches")
    public Branch[] getAllBranchesFromPublicRepos(@RequestParam("username") String username, @RequestParam("repo") String repo) {
        Branch[] results = this.gitService.getAllBranchesFromPublicRepos(username, repo);
        return results;
    }

    @GetMapping("/git-repo/all/branches")
    public Branch[] getAllBranchesFromAllRepos(@RequestHeader("token") String token, @RequestParam("username") String username, @RequestParam("repo") String repo) {
        Branch[] results = this.gitService.getAllBranchesFromRepo(token, username, repo);
        return results;
    }
}
