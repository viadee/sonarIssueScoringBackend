package de.viawhs.backend.model;

import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.stereotype.Component;

public final class FilePredictionParams {
    private final int predictionHorizon;
    private final ServerInfo gitServer;
    private final String h2oUrl;

    private FilePredictionParams(int predictionHorizon, ServerInfo gitServer, String h2oUrl) {
        this.predictionHorizon = predictionHorizon;
        this.gitServer = Objects.requireNonNull(gitServer, "gitServer");
        this.h2oUrl = Objects.requireNonNull(h2oUrl, "h2oUrl");
    }

    @JsonCreator public static FilePredictionParams of(int predictionHorizon, ServerInfo gitServer, String h2oUrl) {
        return new FilePredictionParams(predictionHorizon, gitServer, h2oUrl);
    }

    public int predictionHorizon() {
        return predictionHorizon;
    }

    public ServerInfo gitServer() {
        return gitServer;
    }

    public String h2oUrl() {
        return h2oUrl;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FilePredictionParams that = (FilePredictionParams) o;
        return predictionHorizon == that.predictionHorizon && gitServer.equals(that.gitServer) && h2oUrl.equals(that.h2oUrl);
    }

}

