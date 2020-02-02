package de.viawhs.backend.rest;

import de.viawhs.backend.service.AnalyticsService;
import de.viawhs.backend.service.FileService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AnalyticsController.class)
public class AnalyticsControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AnalyticsService analyticsService;

    @MockBean
    private FileService fileService;

    @Test
    public void noDatas() throws Exception {
        mockMvc.perform(
                post("/server/analytics/change-count"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testChangeCount() throws Exception {
        JSONObject testObject = new JSONObject();
        testObject.put("url", "https://github.com/apache/commons-io");
        testObject.put("user", "Testuser5678");
        testObject.put("branch", "master");
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
        mockMvc.perform(
                post("/server/analytics/change-count")
                        .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(testObject)))
                .andExpect(status().isOk());
    }
}
