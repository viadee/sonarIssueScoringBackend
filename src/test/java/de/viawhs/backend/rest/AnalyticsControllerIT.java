package de.viawhs.backend.rest;

import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnalyticsControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testChangeCount() throws Exception{
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject testObject=new JSONObject();
        testObject.put("url", "https://github.com/apache/commons-io");
        testObject.put("user", "Testuser5678");
        testObject.put("branch","master");
        testObject.put("h2o", "http://localhost");
        testObject.put("port", 54321);
        testObject.put("horizon", 256);
        testObject.put("filenamePrefix", false);
        testObject.put("filenamePostfix", false);
        testObject.put("isPackage", false);
        testObject.put("dependenciesExternal", false);
        testObject.put("dependenciesInternal", false);
        testObject.put("complexity", false);
        testObject.put("lines", false);
        testObject.put("author", false);
        testObject.put("comments", false);
        testObject.put("weekday", false);

        HttpEntity<String> request=new HttpEntity<String>(testObject.toString(),headers);
        String result = this.restTemplate.postForObject("http://localhost:3000/server/analytics/change-count",request,String.class);
        assertEquals("Test",result);
    }
}
