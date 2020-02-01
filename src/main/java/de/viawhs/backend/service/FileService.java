package de.viawhs.backend.service;

import de.viawhs.backend.model.ResultFile;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {
    private final String DIRECTORY = "src/main/java/de/viawhs/backend/results/";

    public boolean saveStringToFile(String text, String name, String dir) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dir + name));
            writer.write(text);
            writer.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean saveResultFile(ResultFile file) {
        try {
            String name = file.getName() + "_" + file.getDate() + "_" + file.getRepository();
            BufferedWriter writer = new BufferedWriter(new FileWriter(DIRECTORY + name + ".txt"));
            writer.write(file.getResult());
            writer.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<String> getAllFilesInDirectory(String dir) {
        try (Stream<Path> walk = Files.walk(Paths.get(dir))) {
            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString().split("/")[x.toString().split("/").length-1]).collect(Collectors.toList());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ResultFile> getAllResultFiles(String dir) {
        List<ResultFile> results = new ArrayList<>();
        List<String> fileNames = getAllFilesInDirectory(dir);
        for (String fileName : fileNames) {
            String result = readFile(dir + "/" + fileName);
            String username = fileName.split("_")[0];
            String date = fileName.split("_")[1];
            String repository = fileName.split("_")[2];
            ResultFile resultFile = new ResultFile(username, date, repository, result);
            results.add(resultFile);
        }
        return results;
    }


    public String readFile(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
