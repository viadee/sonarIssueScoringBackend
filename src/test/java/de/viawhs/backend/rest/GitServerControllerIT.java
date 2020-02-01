package de.viawhs.backend.rest;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitServerControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    /* Getting all public repositories*/
    @Test
    public void testPublicRepos() throws JSONException {
        String responsePublicReposFilled = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public?username=Testuser5678", String.class);
        JSONAssert.assertEquals(
                "[{id:235550014}, {id:235558785} ]",
                responsePublicReposFilled,
                false);
    }

    /*Getting public repositories with empty git account*/
    @Test
    public void testPublicReposEmpty() throws JSONException {
        String responsePublicReposEmpty = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public?username=k-backes", String.class);
        JSONAssert.assertEquals(
                "[]", responsePublicReposEmpty, false);
    }


    /*Getting all branches of a public repository*/
    @Test
    public void testAllPublicBranches() throws JSONException {
        String responseBranchesPublic = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public/branches?username=Testuser5678&repo=testrepository",
                String.class);
        JSONAssert.assertEquals("[{name:master}]", responseBranchesPublic, false);

    }

}
