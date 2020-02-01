package de.viawhs.backend.model;

import java.util.Date;

public class ResultFile {
    private String name;
    private String date;
    private String repository;
    private String result;

    public ResultFile() {
    }

    public ResultFile(String name, String date, String repository, String result) {
        this.name = name;
        this.date = date;
        this.repository = repository;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getRepository() {
        return repository;
    }

    public String getResult() {
        return result;
    }
}
