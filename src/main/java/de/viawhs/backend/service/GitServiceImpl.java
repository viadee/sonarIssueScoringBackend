package de.viawhs.backend.service;

import de.viawhs.backend.model.Repository;
import de.viawhs.backend.model.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        /*
        ResponseEntity<List<Repository>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Repository>>() {});
        List<Repository> objects = responseEntity.getBody();
        */
        ResponseEntity<Repository[]> response = restTemplate.getForEntity(url, Repository[].class);
        return response;
    }
}
