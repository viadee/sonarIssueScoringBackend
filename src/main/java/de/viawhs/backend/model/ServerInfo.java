package de.viawhs.backend.model;

public class ServerInfo {
    private String url;

    public ServerInfo() {}

    public ServerInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
