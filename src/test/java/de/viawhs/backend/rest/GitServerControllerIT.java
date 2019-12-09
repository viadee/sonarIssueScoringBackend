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

    @Test
    public void contextLoad() throws JSONException {

        /*Beginning Tests: Getting all public repositories*/
        String responsePublicReposFilled = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public?username=trnhan251", String.class);
        JSONAssert.assertEquals(
                "[{id:191923132}, {id:181204192}, {id:203966717}, {id:215288135}]",
                responsePublicReposFilled,
                false);

        String responsePublicReposEmpty = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public?username=k-backes", String.class);
        JSONAssert.assertEquals(
                "[]", responsePublicReposEmpty, false);

        String responsePublicWrongUser = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public?username=khfslhdfhsdfhdsfhdfjdfjdjfhsdfk", String.class);
        JSONAssert.assertEquals("{status:500}", responsePublicWrongUser, false);
        /*End Tests: Getting all public repositories*/


        /*Beginning Tests: Getting all public and private repositories with a token string*/
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", "98e129228e935775e8a61ed126d29972e72062b0" );
        HttpEntity entity = new HttpEntity (headers);
        ResponseEntity<String> responseallrepositories = this.restTemplate.exchange(
                "http://localhost:3000/server/git-repo/all" ,
                HttpMethod.GET,
                entity,
                String.class);
        JSONAssert.assertEquals("[{id:194104724}, {id:141591060}, {id:217829110}, {id:218061522}]", responseallrepositories.getBody(), false);

        headers = new HttpHeaders();
        headers.set("token", "1234567890");
        entity = new HttpEntity(headers);
        ResponseEntity<String> responseAllRepositoriesWrongToken = this.restTemplate.exchange(
                "http://localhost:3000/server/git-repo/all",
                HttpMethod.GET,
                entity,
                String.class);
        JSONAssert.assertEquals("{status: 500}", responseAllRepositoriesWrongToken.getBody(), false);
        /*End Tests: Getting all public and private repositories with a token string*/


        /*Beginning Tests: Getting all branches of a public repository*/
        String responseBranchesPublic = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public/branches?username=trnhan251&repo=sonarIssueScoringBackend",
                String.class);
        JSONAssert.assertEquals("[{name:master}]", responseBranchesPublic, false);

        String responseWrongBranchPublic = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public/branches?username=trnhan251&repo=wrongbranchname", String.class);
        JSONAssert.assertEquals("{status:500}", responseWrongBranchPublic, false);
        /*End Tests: Getting all branches of a public repository*/


        /*Beginning Tests: Getting all branches of a public or private repository from a user with username and token*/
        HttpHeaders headersuser = new HttpHeaders();
        headersuser.set("token", "98e129228e935775e8a61ed126d29972e72062b0" );
        HttpEntity entityuser = new HttpEntity (headersuser);
        ResponseEntity<String> responseallrepositoriesuser = this.restTemplate.exchange(
                "http://localhost:3000/server/git-repo/all/branches?username=k-backes&repo=sonarIssueScoringBackend" ,
                HttpMethod.GET,
                entityuser,
                String.class);
        JSONAssert.assertEquals("{}", responseallrepositoriesuser.getBody(), false);
        /*End Tests: Getting all branches of a public or private repository from a user with username and token*/
    }

}
