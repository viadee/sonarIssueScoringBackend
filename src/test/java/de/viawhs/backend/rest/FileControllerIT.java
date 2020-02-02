package de.viawhs.backend.rest;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    @Test
    public void testGetAllFiles() throws Exception {
        mockMvc.perform(
                get("/server/files/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAllResults() throws Exception {
        mockMvc.perform(
                get("/server/files/all-results"))
                .andExpect(status().isOk());
    }

    @Test
    public void testReadFile() throws Exception {
        mockMvc.perform(
                get("/server/files?name=testFile"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveResultsNoData() throws Exception{
        mockMvc.perform(
                post("/server/files/save-result?name=testFile"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveResults() throws Exception{
        String test="This is the result of saving in this file.";
        mockMvc.perform(post("/server/files/save-result?name=testFile")
                .contentType(MediaType.TEXT_HTML).content(test))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveResultFilesNoData() throws Exception{
        mockMvc.perform(
                post("/server/files/save-result-file"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveResultFiles() throws Exception{
        JSONObject testObject= new JSONObject();
        testObject.put("name","testFile");
        testObject.put("date","01-02-2020-07-40-43");
        testObject.put("repository","commons-io.txt");
        testObject.put("result","");
        mockMvc.perform(post("/server/files/save-result-file")
                .contentType(MediaType.APPLICATION_JSON).content(String.valueOf(testObject)))
                .andExpect(status().isOk());
    }
}
