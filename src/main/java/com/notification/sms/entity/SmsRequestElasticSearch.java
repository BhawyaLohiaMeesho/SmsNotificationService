package com.notification.sms.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.*;
import java.time.LocalDateTime;

@Document(indexName = "sms_requests")
public class SmsRequestElasticSearch {

    @Id
    private Integer id;

    private String phoneNumber;

    private String message;

    private String status;

    private String failureCode;

    private String failureComments;

    @Field(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Field(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public SmsRequestElasticSearch(){}

    public SmsRequestElasticSearch(Integer id, String phoneNumber, String message, String status,
                                   String failureCode, String failureComments, LocalDateTime createdAt,
                                   LocalDateTime updatedAt) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.status = status;
        this.failureCode = failureCode;
        this.failureComments = failureComments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public String getFailureComments() {
        return failureComments;
    }

    public void setFailureComments(String failureComments) {
        this.failureComments = failureComments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SmsRequestElasticSearch{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", failureCode='" + failureCode + '\'' +
                ", failureComments='" + failureComments + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
