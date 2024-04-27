package com.example.sonrisaplantjv.common;


import com.google.gson.annotations.SerializedName;

public class ActionResponse<TPayload> {
    @SerializedName("success")
    private boolean success;

    @SerializedName("errorCode")
    private int errorCode;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private TPayload data;

    public ActionResponse() {
        this.success = false;
        this.errorCode = 0;
        this.message = "";
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TPayload getData() {
        return data;
    }

    public void setData(TPayload data) {
        this.data = data;
    }
}
