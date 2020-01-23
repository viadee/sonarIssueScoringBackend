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
        return getResult(wizard, url, headers, object);
    }

    public String orderingIssues(Wizard wizard) {
        String url = "http://localhost:5432/issues/desirability";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject object = new JSONObject();
        ServerInfo sonarServer = new ServerInfo("https://sonarcloud.io");
        object.put("sonarServer", sonarServer);
        object.put("sonarProjectId", "commons-io_180410");
        return getResult(wizard, url, headers, object);
    }

    private String getResult(Wizard wizard, String url, HttpHeaders headers, JSONObject object) {
        object.put("predictionHorizon", wizard.getPredictionHorizon());
        object.put("gitServer", wizard.getGitServer());
        object.put("h2oUrl", wizard.getH2oUrl());

        HttpEntity<String> entity = new HttpEntity<String>(object.toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();
    }

}
