package com.lyy.autointerface.parameters;

/**
 * 在Java中json对象都是放在Java Bean类中，通过JSON把高级对象序列化成json对象
 * @author liyingying
 *
 */
public class User {
    private String name;
    private String job;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public User(String name, String job) {
        super();
        this.name = name;
        this.job = job;
    }

    public User() {
        // TODO Auto-generated constructor stub
    }
}

