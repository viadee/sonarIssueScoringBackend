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
                "http://localhost:3000/server/git-repo/public?username=Testuser5678", String.class);
        JSONAssert.assertEquals(
                "[{id:235550014}, {id:235558785} ]",
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


        /*Beginning Tests: Getting all branches of a public repository*/
        String responseBranchesPublic = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public/branches?username=Testuser5678&repo=testrepository",
                String.class);
        JSONAssert.assertEquals("[{name:master}]", responseBranchesPublic, false);

        String responseWrongBranchPublic = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public/branches?username=Testuser5678&repo=wrongbranchname", String.class);
        JSONAssert.assertEquals("{status:500}", responseWrongBranchPublic, false);
        /*End Tests: Getting all branches of a public repository*/



    }

}
