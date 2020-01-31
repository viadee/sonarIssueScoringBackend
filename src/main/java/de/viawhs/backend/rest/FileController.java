package de.viawhs.backend.rest;

import de.viawhs.backend.service.FileService;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

@RestController
@RequestMapping("/server/files")
public class FileController {
    private FileService fileService;
    private final String DIRECTORY = "src/main/java/de/viawhs/backend/results/";

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/all")
    public List<String> getAllFiles() {
        return this.fileService.getAllFilesInDirectory(DIRECTORY);
    }

    @GetMapping("")
    public String readFile(@RequestParam String name) {
        return this.fileService.readFile(DIRECTORY + name);
    }

    @PostMapping("/save-result")
    public boolean savingResult(@RequestBody String result, @RequestParam String name) {
        return this.fileService.saveStringToFile(result, name, DIRECTORY);
    }

}
