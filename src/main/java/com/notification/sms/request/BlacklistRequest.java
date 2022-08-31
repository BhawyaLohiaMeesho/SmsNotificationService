package com.notification.sms.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notification.sms.entity.PhoneNumber;

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

    public void checkRequiredValues() throws Exception{
        if(phoneNumbers==null)
            throw new Exception("List is null");

        for (String phoneNumber : phoneNumbers){
            if(phoneNumber==null){
                throw new Exception("null value present in list");
            }
        }
    }

    @Override
    public String toString() {
        return "BlacklistRequest:{" +
                "phoneNumbers : " + phoneNumbers +
                '}';
    }
}
