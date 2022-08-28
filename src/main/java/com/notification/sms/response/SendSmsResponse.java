package com.notification.sms.response;

public class SendSmsResponse {
    private Integer requestId;
    private String comment;

    public SendSmsResponse(){}
    public SendSmsResponse(Integer requestId, String comment) {
        this.requestId = requestId;
        this.comment = comment;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "SendSmsResponse{" +
                "requestId='" + requestId + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
