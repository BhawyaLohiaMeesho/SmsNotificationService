package com.notification.sms.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BlacklistRequest {
    @JsonProperty("phone_numbers")
    private List<String> phoneNumbers;

    public BlacklistRequest(){}

    public BlacklistRequest(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "BlacklistRequest:{" +
                "phoneNumbers : " + phoneNumbers +
                '}';
    }
}
