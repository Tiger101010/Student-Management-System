package model.config;

public class SQLConfig {

    public String name = null;
    public String url = null;
    public String port = null;
    public String user = null;
    public String password = null;

    public SQLConfig(String conName, String url, String port, String user, String password) {
        this.name = conName;
        this.url = url;
        this.port = port;
        this.user = user;
        this.password = password;
    }
}