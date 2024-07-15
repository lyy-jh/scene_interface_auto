package com.lyy.autointerface.base;

public class Log {
    private String brand;
    private String page;
    private String resolution;
    private String pf;
    private String product;
    private String idfa;
    private String channel;
    private String net;
    private String uid;
    private String os_version;
    private String app_version;
    private String device;
    private String session_id;
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public String getResolution() {
        return resolution;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    public String getPf() {
        return pf;
    }
    public void setPf(String pf) {
        this.pf = pf;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public String getIdfa() {
        return idfa;
    }
    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getNet() {
        return net;
    }
    public void setNet(String net) {
        this.net = net;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getOs_version() {
        return os_version;
    }
    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }
    public String getApp_version() {
        return app_version;
    }
    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }
    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        this.device = device;
    }
    public String getSession_id() {
        return session_id;
    }
    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
    public Log(String brand, String page, String resolution, String pf, String product, String idfa, String channel,
               String net, String uid, String os_version, String app_version, String device, String session_id) {
        super();
        this.brand = brand;
        this.page = page;
        this.resolution = resolution;
        this.pf = pf;
        this.product = product;
        this.idfa = idfa;
        this.channel = channel;
        this.net = net;
        this.uid = uid;
        this.os_version = os_version;
        this.app_version = app_version;
        this.device = device;
        this.session_id = session_id;
    }

    public Log() {
        super();
    }



}

