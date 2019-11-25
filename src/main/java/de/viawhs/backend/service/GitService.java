package de.viawhs.backend.service;

import de.viawhs.backend.model.Branch;
import de.viawhs.backend.model.Repository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GitService {
    ResponseEntity<Repository[]> getAllPublicRepositories(String username);
    ResponseEntity<Repository[]> getAllRepositories(String token);
    ResponseEntity<Branch[]> getAllBranchesFromPublicRepos(String username, String repo);
    ResponseEntity<Branch[]> getAllBranchesFromRepo(String token, String username, String repo);
}
