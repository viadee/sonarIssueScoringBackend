package de.viawhs.backend.rest;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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


        /*Beginning Tests: Getting all branches of a public repository*/
        String responseBranchesPublic = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public/branches?username=trnhan251&repo=sonarIssueScoringBackend",
                String.class);
        JSONAssert.assertEquals("[{name:master}]", responseBranchesPublic, false);

        String responseWrongBranchPublic = this.restTemplate.getForObject(
                "http://localhost:3000/server/git-repo/public/branches?username=trnhan251&repo=wrongbranchname", String.class);
        JSONAssert.assertEquals("{status:500}", responseWrongBranchPublic, false);
        /*End Tests: Getting all branches of a public repository*/

    }

}
