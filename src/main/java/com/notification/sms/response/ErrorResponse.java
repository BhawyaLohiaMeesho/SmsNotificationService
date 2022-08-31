package com.notification.sms.response;

public class ErrorResponse {
    private ErrorResponseDetails error;

    public ErrorResponse(){}

    public ErrorResponse(ErrorResponseDetails error) {
        this.error = error;
    }

    public ErrorResponseDetails getError() {
        return error;
    }

    public void setError(ErrorResponseDetails error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error=" + error +
                '}';
    }
}
