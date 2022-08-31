package com.notification.sms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="blacklist")
public class PhoneNumber {
    @Id
    @Column(name="phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    public PhoneNumber(){}
    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static boolean isValid(String phoneNumber){
        return true;
    }
    @Override
    public String toString() {
        return "PhoneNumber: {" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
