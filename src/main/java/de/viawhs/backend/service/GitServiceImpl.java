package de.viawhs.backend.service;

import de.viawhs.backend.model.Repository;
import de.viawhs.backend.model.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GitServiceImpl implements GitService{
    private ServerInfo gitServer;
    private RestTemplate restTemplate;

    public GitServiceImpl() {
        restTemplate = new RestTemplate();
    }

    public ResponseEntity<Repository[]> getAllPublicRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        ResponseEntity<Repository[]> response = restTemplate.getForEntity(url, Repository[].class);
        return response;
    }

    public ResponseEntity<Repository[]> getAllRepositories(String token) {
        String url = "https://api.github.com/user/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<Repository[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Repository[].class);
        return responseEntity;
    }
}
