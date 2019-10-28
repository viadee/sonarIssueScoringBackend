package de.viawhs.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class User {
    private String login;
    private int id;
    private String url;

    public User() {}

    public User(String login, int id, String url) {
        this.login = login;
        this.id = id;
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "[" + this.login + " ; " + this.url  + " ; " + this.id + "]";
    }
}
