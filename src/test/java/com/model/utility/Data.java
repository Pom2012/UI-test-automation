package com.model.utility;

public class Data {
    private String appName;
    private String templateName;
    private String userId;

    private String UserID;
    private String ApplicationName;
    private String Role;

    public Data(String userID, String applicationName, String role, String requestID, String requestStatus) {
        UserID = userID;
        ApplicationName = applicationName;
        Role = role;
        this.requestID = requestID;
        this.requestStatus = requestStatus;
    }

    private String requestID;
    private String requestDate;
    private String requestStatus;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getApplicationName() {
        return ApplicationName;
    }

    public void setApplicationName(String applicationName) {
        ApplicationName = applicationName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }


    public Data(String appName, String templateName, String userId) {
        this.appName = appName;
        this.templateName = templateName;
        this.userId = userId;
    }

    public Data() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{\"appName\":\"" + appName + "\"," +
                "\"templateName\":\"" + templateName + "\", " +
                "\"userId\":\"" + userId + "\"" +
                "}";
    }
}
