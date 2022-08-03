package com.nihongo.model;

public class ExecutionResult {

    private boolean success;
    private String detail;
    private String dateTime;

    public ExecutionResult(boolean success, String detail, String dateTime) {
        this.success = success;
        this.detail = detail;
        this.dateTime = dateTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
