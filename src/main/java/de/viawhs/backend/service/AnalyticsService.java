package de.viawhs.backend.service;

import de.viawhs.backend.model.ServerInfo;
import de.viawhs.backend.model.Wizard;
import net.minidev.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalyticsService {
    RestTemplate restTemplate;

    public AnalyticsService() {
        this.restTemplate = new RestTemplate();
    }

    public String predictChangeCount(Wizard wizard) {
        String url = "http://localhost:5432/files/predict";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject object = new JSONObject();
        object.put("predictionHorizon", 256);
        ServerInfo serverInfo = new ServerInfo("https://github.com/apache/commons-io");
        object.put("gitServer", serverInfo);
        object.put("h2oUrl", "http://localhost:54321");
        HttpEntity<String> entity = new HttpEntity<String>(object.toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();
    }

    public String orderingIssues(Wizard wizard) {
        String url = "http://localhost:5432/issues/desirability";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject object = new JSONObject();
        ServerInfo sonarServer = new ServerInfo("https://sonarcloud.io");
        object.put("sonarServer", sonarServer);
        object.put("sonarProjectId", "commons-io_180410");
        object.put("predictionHorizon", 256);
        ServerInfo gitServer = new ServerInfo("https://github.com/apache/commons-io");
        object.put("gitServer", gitServer);
        object.put("h2oUrl", "http://localhost:54321");
        HttpEntity<String> entity = new HttpEntity<String>(object.toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();
        //ResponseEntity<DesirabilityResult> responseEntity = restTemplate.postForEntity(url, entity, DesirabilityResult.class);
        //return responseEntity.getBody();
    }
}
