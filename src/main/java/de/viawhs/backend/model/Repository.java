package de.viawhs.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class Repository {
    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("full_name") private String full_name;
    @JsonProperty("owner") private User owner;

    public Repository() {}

    public Repository(int id, String name, String full_name, User owner) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
