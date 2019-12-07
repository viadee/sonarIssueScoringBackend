package de.viawhs.backend.rest;

import de.viawhs.backend.model.Branch;
import de.viawhs.backend.model.Repository;
import de.viawhs.backend.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server/git-repo")
public class GitServerController {
    private GitService gitService;

    @Autowired
    public GitServerController(GitService gitService) {
        this.gitService = gitService;
    }

    // Get all public repositories with a username
    @GetMapping("/public")
    public Repository[] getAllPublicRepositories(@RequestParam("username") String username) {
        Repository[] results = this.gitService.getAllPublicRepositories(username);
        return results;
    }

    // Get all public and private repositories with a token
    @GetMapping("/all")
    public Repository[] getAllRepositories(@RequestHeader("token") String token) {
        Repository[] results = this.gitService.getAllRepositories(token);
        return results;
    }

    @GetMapping("/public/branches")
    public Branch[] getAllBranchesFromPublicRepos(@RequestParam("username") String username, @RequestParam("repo") String repo) {
        Branch[] results = this.gitService.getAllBranchesFromPublicRepos(username, repo);
        return results;
    }

    @GetMapping("/all/branches")
    public Branch[] getAllBranchesFromAllRepos(@RequestHeader("token") String token, @RequestParam("username") String username, @RequestParam("repo") String repo) {
        Branch[] results = this.gitService.getAllBranchesFromRepo(token, username, repo);
        return results;
    }
}
