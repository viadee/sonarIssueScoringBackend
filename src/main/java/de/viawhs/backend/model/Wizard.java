package de.viawhs.backend.model;

public class Wizard {
    private String url;
    private String user;
    private String branch;
    private String h2o;
    private int port;
    private int horizon;
    private boolean filenamePrefix;
    private boolean filenamePostfix;
    private boolean isPackage;
    private boolean dependenciesExternal;
    private boolean dependenciesInternal;
    private boolean complexity;
    private boolean lines;
    private boolean author;
    private boolean comments;
    private boolean weekday;

    /*
    private int predictionHorizon;
    private ServerInfo gitServer;
    private String h2oUrl;*/

    public Wizard() {
    }

    public Wizard(String url, String user, String branch, String h2o, int port, int horizon) {
        this.url = url;
        this.user = user;
        this.branch = branch;
        this.h2o = h2o;
        this.port = port;
        this.horizon = horizon;
    }

    public Wizard(String url, String user, String branch, String h2o, int port, int horizon, boolean filenamePrefix, boolean filenamePostfix, boolean isPackage, boolean dependenciesExternal, boolean dependenciesInternal, boolean complexity, boolean lines, boolean author, boolean comments, boolean weekday) {
        this.url = url;
        this.user = user;
        this.branch = branch;
        this.h2o = h2o;
        this.port = port;
        this.horizon = horizon;
        this.filenamePrefix = filenamePrefix;
        this.filenamePostfix = filenamePostfix;
        this.isPackage = isPackage;
        this.dependenciesExternal = dependenciesExternal;
        this.dependenciesInternal = dependenciesInternal;
        this.complexity = complexity;
        this.lines = lines;
        this.author = author;
        this.comments = comments;
        this.weekday = weekday;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getH2o() {
        return h2o;
    }

    public void setH2o(String h2o) {
        this.h2o = h2o;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHorizon() {
        return horizon;
    }

    public void setHorizon(int horizon) {
        this.horizon = horizon;
    }

    public boolean isFilenamePrefix() {
        return filenamePrefix;
    }

    public void setFilenamePrefix(boolean filenamePrefix) {
        this.filenamePrefix = filenamePrefix;
    }

    public boolean isFilenamePostfix() {
        return filenamePostfix;
    }

    public void setFilenamePostfix(boolean filenamePostfix) {
        this.filenamePostfix = filenamePostfix;
    }

    public boolean isPackage() {
        return isPackage;
    }

    public void setPackage(boolean aPackage) {
        isPackage = aPackage;
    }

    public boolean isDependenciesExternal() {
        return dependenciesExternal;
    }

    public void setDependenciesExternal(boolean dependenciesExternal) {
        this.dependenciesExternal = dependenciesExternal;
    }

    public boolean isDependenciesInternal() {
        return dependenciesInternal;
    }

    public void setDependenciesInternal(boolean dependenciesInternal) {
        this.dependenciesInternal = dependenciesInternal;
    }

    public boolean isComplexity() {
        return complexity;
    }

    public void setComplexity(boolean complexity) {
        this.complexity = complexity;
    }

    public boolean isLines() {
        return lines;
    }

    public void setLines(boolean lines) {
        this.lines = lines;
    }

    public boolean isAuthor() {
        return author;
    }

    public void setAuthor(boolean author) {
        this.author = author;
    }

    public boolean isComments() {
        return comments;
    }

    public void setComments(boolean comments) {
        this.comments = comments;
    }

    public boolean isWeekday() {
        return weekday;
    }

    public void setWeekday(boolean weekday) {
        this.weekday = weekday;
    }
}
