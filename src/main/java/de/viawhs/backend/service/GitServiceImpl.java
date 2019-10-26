package de.viawhs.backend.service;

import de.viawhs.backend.model.ServerInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitServiceImpl implements GitService{
    private ServerInfo gitServer;
    private final RestTemplate restTemplate;

    public GitServiceImpl(RestTemplateBuilder templateBuilder) {
        restTemplate = templateBuilder.build();
    }

    public String getAllPublicRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        return this.restTemplate.getForObject(url, String.class);
    }
}
