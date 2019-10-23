package de.viawhs.backend.service;

import de.viawhs.backend.model.FilePredictionParams;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChangePredictionService {
    public ResponseEntity<String> callChangePrediction(FilePredictionParams params) {
        final String uri = "http://localhost:5432/files/predict";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, params, String.class);
        return result;
    }
}
