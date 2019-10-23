package de.viawhs.backend.web;

import de.viawhs.backend.model.FilePredictionParams;
import de.viawhs.backend.service.ChangePredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePredictionController {
    private ChangePredictionService changePredictionService;

    @PostMapping("/change-prediction")
    private ResponseEntity<String> PostChangePrediction(@RequestBody FilePredictionParams params) {
        ResponseEntity<String> result = changePredictionService.callChangePrediction(params);
        return result;
    }
}
