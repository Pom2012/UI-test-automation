package com.model.pages.appConsole;

public class Requests extends ApplicationConsolePages {
    private String reqID;

    public Requests(String reqID) {
        this.reqID = reqID;
    }

    public Requests() {

    }

    @Override
    public String toString() {
        return "Requests{" +
                "reqID='" + reqID + '\'' +
                '}';
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }
}
