package de.viawhs.backend.service;

import de.viawhs.backend.model.Repository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GitService {
    public ResponseEntity<Repository[]> getAllPublicRepositories(String username);
    public ResponseEntity<Repository[]> getAllRepositories(String token);
}
