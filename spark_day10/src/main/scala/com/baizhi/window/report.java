package com.baizhi.window;

import java.util.Date;

public class report {
    private Integer ID;
    private String app;
    private Boolean evaluate;
    private Boolean login_habbit;
    private Boolean input_future;
    private Boolean input_pwd;
    private Boolean login_area;
    private Boolean device_info;
    private Date evaluate_time;

    public report(Integer ID, String app, Boolean evaluate, Boolean login_habbit, Boolean input_future, Boolean input_pwd, Boolean login_area, Boolean device_info, Date evaluate_time) {
        this.ID = ID;
        this.app = app;
        this.evaluate = evaluate;
        this.login_habbit = login_habbit;
        this.input_future = input_future;
        this.input_pwd = input_pwd;
        this.login_area = login_area;
        this.device_info = device_info;
        this.evaluate_time = evaluate_time;
    }

    public report() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public Boolean getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Boolean evaluate) {
        this.evaluate = evaluate;
    }

    public Boolean getLogin_habbit() {
        return login_habbit;
    }

    public void setLogin_habbit(Boolean login_habbit) {
        this.login_habbit = login_habbit;
    }

    public Boolean getInput_future() {
        return input_future;
    }

    public void setInput_future(Boolean input_future) {
        this.input_future = input_future;
    }

    public Boolean getInput_pwd() {
        return input_pwd;
    }

    public void setInput_pwd(Boolean input_pwd) {
        this.input_pwd = input_pwd;
    }

    public Boolean getLogin_area() {
        return login_area;
    }

    public void setLogin_area(Boolean login_area) {
        this.login_area = login_area;
    }

    public Boolean getDevice_info() {
        return device_info;
    }

    public void setDevice_info(Boolean device_info) {
        this.device_info = device_info;
    }

    public Date getEvaluate_time() {
        return evaluate_time;
    }

    public void setEvaluate_time(Date evaluate_time) {
        this.evaluate_time = evaluate_time;
    }

    @Override
    public String toString() {
        return "report{" +
                "ID=" + ID +
                ", app='" + app + '\'' +
                ", evaluate=" + evaluate +
                ", login_habbit=" + login_habbit +
                ", input_future=" + input_future +
                ", input_pwd=" + input_pwd +
                ", login_area=" + login_area +
                ", device_info=" + device_info +
                ", evaluate_time=" + evaluate_time +
                '}';
    }
}
