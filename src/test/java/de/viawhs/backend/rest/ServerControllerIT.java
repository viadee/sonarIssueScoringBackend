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
public class ServerControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoad() throws JSONException {
        String response = this.restTemplate.getForObject("/api/server/git-repo?username=trnhan251", String.class);
        JSONAssert.assertEquals("[{id:191923132}, {id:181204192}, {id:203966717}, {id:215288135}]", response, false);
    }

}
