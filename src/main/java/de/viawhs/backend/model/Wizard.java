package de.viawhs.backend.model;

public class Wizard {
    private int predictionHorizon;
    private ServerInfo gitServer;
    private String h2oUrl;

    public Wizard() {
    }

    public Wizard(int predictionHorizon, ServerInfo gitServer, String h2oUrl) {
        this.predictionHorizon = predictionHorizon;
        this.gitServer = gitServer;
        this.h2oUrl = h2oUrl;
    }

    public int getPredictionHorizon() {
        return predictionHorizon;
    }

    public void setPredictionHorizon(int predictionHorizon) {
        this.predictionHorizon = predictionHorizon;
    }

    public ServerInfo getGitServer() {
        return gitServer;
    }

    public void setGitServer(ServerInfo gitServer) {
        this.gitServer = gitServer;
    }

    public String getH2oUrl() {
        return h2oUrl;
    }

    public void setH2oUrl(String h2oUrl) {
        this.h2oUrl = h2oUrl;
    }
}
