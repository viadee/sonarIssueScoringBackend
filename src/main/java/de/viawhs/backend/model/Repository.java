package de.viawhs.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class Repository {
    private int id;
    private String name;
    private String full_name;
    private User owner;
    private List<Branch> branches;

    public Repository() {}

    public Repository(int id, String name, String full_name, User owner, List<Branch> branches) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.owner = owner;
        this.branches = branches;
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

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
