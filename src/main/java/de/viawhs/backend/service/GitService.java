package de.viawhs.backend.service;

import de.viawhs.backend.model.Branch;
import de.viawhs.backend.model.Repository;
import de.viawhs.backend.model.ServerInfo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GitService {
    private ServerInfo gitServer;
    private RestTemplate restTemplate;

    public GitService() {
        restTemplate = new RestTemplate();
    }

    public Repository[] getAllPublicRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        ResponseEntity<Repository[]> response = restTemplate.getForEntity(url, Repository[].class);
        return response.getBody();
    }

    public Repository[] getAllRepositories(String token) {
        String url = "https://api.github.com/user/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<Repository[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Repository[].class);

        if (responseEntity.getBody() != null)
            for (Repository repo : responseEntity.getBody()) {
                ResponseEntity<Branch[]> branches = getAllBranches(repo);
                if (branches != null)
                    if (branches.getBody() != null)
                        repo.setBranches(Arrays.asList(branches.getBody()));
            }

        return responseEntity.getBody();
    }

    public Branch[] getAllBranchesFromPublicRepos(String username, String repo) {
        String url = "https://api.github.com/repos/" + username + "/" + repo + "/branches";
        ResponseEntity<Branch[]> response = null;
        try {
            response = restTemplate.getForEntity(url, Branch[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

    public Branch[] getAllBranchesFromRepo(String token, String username, String repo) {
        String url = "https://api.github.com/repos/" + username + "/" + repo + "/branches";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Branch[]> response = null;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, Branch[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response.getBody();
    }

    public ResponseEntity<Branch[]> getAllBranches(Repository repository) {
        String url = "https://api.github.com/repos/"+ repository.getOwner().getLogin()  +"/" + repository.getName() + "/branches";
        ResponseEntity<Branch[]> response = null;
        try {
            response = restTemplate.getForEntity(url, Branch[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private boolean isServerAvailable(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        try {
            ResponseEntity<Repository[]> response = restTemplate.getForEntity(url, Repository[].class);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
