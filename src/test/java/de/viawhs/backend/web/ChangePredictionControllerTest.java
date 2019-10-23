package de.viawhs.backend.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ChangePredictionController.class)
public class ChangePredictionControllerTest {
    @Autowired private MockMvc mvc;

    // Check if it returns OK status when call /change-prediction
    @Test
    public void getChangePrediction() throws Exception {

        String content = "{ \n" +
                "   \"predictionHorizon\":256,\n" +
                "   \"gitServer\":{ \n" +
                "      \"url\":\"https://github.com/apache/commons-io\"\n" +
                "   },\n" +
                "   \"h2oUrl\":\"http://localhost:54321\"\n" +
                "}";

        mvc.perform(MockMvcRequestBuilders
                .post("/change-prediction")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();
    }
}
