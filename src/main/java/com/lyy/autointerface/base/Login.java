package com.lyy.autointerface.base;

public class Login {
    private Log log;
    private String token;
    private String name;
    private String pwd;
    public Log getLog() {
        return log;
    }
    public void setLog(Log log) {
        this.log = log;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Login(Log log, String token, String name, String pwd) {
        super();
        this.log = log;
        this.token = token;
        this.name = name;
        this.pwd = pwd;
    }

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }


}

