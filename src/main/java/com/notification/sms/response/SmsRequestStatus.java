package com.notification.sms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.notification.sms.constant.Data;
import com.notification.sms.entity.SmsRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsRequestStatus {
    private String status;
    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String comments;

    public SmsRequestStatus(){}

    public SmsRequestStatus(String status, String code, String comments) {
        this.status = status;
        this.code = code;
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "SmsRequestStatus{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
