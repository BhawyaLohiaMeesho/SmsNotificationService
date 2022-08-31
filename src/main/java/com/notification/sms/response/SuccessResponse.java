package com.notification.sms.response;


public class SuccessResponse <T> {
    private T data;

    public SuccessResponse(){}
    public SuccessResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SuccessResponse{" +
                "data=" + data +
                '}';
    }
}
