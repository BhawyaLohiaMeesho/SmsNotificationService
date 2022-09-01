package com.notification.sms.entity;

import com.notification.sms.constant.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Document(indexName = Data.SMS_REQUEST_INDEX_ELASTICSEARCH)
@Table(name=Data.SMS_REQUEST_TABLE_MYSQL)
public class SmsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="phone_number",nullable = false)
    private String phoneNumber;

    @Column(name="message",nullable = false)
    private String message;

    @Column(name="status")
    private String status;

    @Column(name="failure_code")
    private String failureCode;

    @Column(name="failure_comments")
    private String failureComments;

    @Column(name="created_at")
    @CreationTimestamp
    @Field(type= FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    @Field(type= FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    public SmsRequest(){
        this.status= Data.SMS_REQUEST_STATUS_QUEUED;
    }

    public SmsRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.status= Data.SMS_REQUEST_STATUS_QUEUED;
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
        return "SmsRequest: {" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", failureCode='" + failureCode + '\'' +
                ", failureComments='" + failureComments + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
